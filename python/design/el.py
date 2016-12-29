import time
import threading
import random
from enum import Enum


class El:

    """
    Assumption:
    A. Two elevators
    B. Unlimited passenger capacity
    C. Loading passenger takes no time

    Design:
    1. Check pool to find idle elevator
    2. For one elevator, ignore any passenger whose destination is not in the same moving direction
    3. For one elevator, check each floor before reach the destination
    4. For one elevator, extends the destination if the incoming passenger's destination is the same moving direction
    5. (Because of assumption C) For one elevator, pick up the passenger whose destination is not in the same moving
    direction of the other one
    """

    queues = {}
    elevators = {}

    # passenger id counter
    pc = 0

    # highest/lowest floor to pick up passenger
    # these're important system variable to determine the pick up elevator
    # should be the idle one or the operating one
    highest_pick_up_floor = -1
    lowest_pick_up_floor = -1

    def __init__(self):
        self.nf = 6
        self.ne = 2
        self.gp = 0.5
        self.ce = 1
        self.duration = 10

        for floor in range(1, self.nf + 1):
            self.queues[floor] = []

        for eid in range(1, self.ne + 1):
            self.elevators[eid] = Elevator(eid)

    # ------------------- Testing functions -------------------
    def run(self):
        print("Start elevator threads...")

        stop = threading.Event()

        self.new_passenger(stop)

        for eid, el in self.elevators.items():
            self.operate_elevator(eid, stop)

        self.stop_timer(stop)
        print("Stop elevator threads...")

    def stop_timer(self, stop):
        start = time.clock()
        end = time.clock()

        while end - start < self.duration:
            end = time.clock()

        stop.set()

    def new_passenger(self, stop):
        floor = random.randint(1, self.nf)
        dest = random.randint(1, self.nf)
        while dest == floor:
            dest = random.randint(1, self.nf)

        print("Created new passenger on floor [{}] with id [{}] and destination [{}]".format(floor, self.pc, dest))

        if floor < self.lowest_pick_up_floor or self.lowest_pick_up_floor == -1:
            self.lowest_pick_up_floor = floor
        elif floor > self.highest_pick_up_floor or self.highest_pick_up_floor == -1:
            self.highest_pick_up_floor = floor

        self.queues[floor].append(Passenger(dest, self.pc))
        self.pc += 1

        if not stop.is_set():
            threading.Timer(interval=self.gp, function=self.new_passenger, args=(stop,)).start()

    def operate_elevator(self, eid, stop):
        self.set_pick_up_floor(eid)
        self.move_elevator(eid)
        self.load_passenger(eid)

        if not stop.is_set():
            threading.Timer(interval=self.ce, function=self.operate_elevator, args=(eid, stop)).start()

    # ------------------- Functions -------------------
    def set_pick_up_floor(self, eid):
        el = self.elevators[eid]

        if el.get_status() == Status.IDLE:
            if self.highest_pick_up_floor != -1:
                el.dest = self.highest_pick_up_floor
                self.highest_pick_up_floor = -1
            elif self.lowest_pick_up_floor != -1:
                el.dest = self.lowest_pick_up_floor
                self.lowest_pick_up_floor = -1

    def move_elevator(self, eid):
        el = self.elevators[eid]
        from_floor = el.current_floor

        if el.get_status() == Status.UP:
            el.current_floor += 1

        elif el.get_status() == Status.DOWN:
            el.current_floor -= 1

        passenger_ids_to_drop = [passenger.pid for passenger in el.passengers if passenger.dest == el.current_floor]
        el.passengers = [passenger for passenger in el.passengers if passenger.dest != el.current_floor]

        if from_floor != el.current_floor:
            print("Elevator [{}] moved to from floor [{}] to floor [{}] and dropped passengers {}"
                  .format(eid, from_floor, el.current_floor, list(passenger_ids_to_drop)))
        else:
            print("Elevator [{}] is idle on floor [{}]".format(eid, el.current_floor))

    def load_passenger(self, eid):
        el = self.elevators[eid]
        passengers_to_load = []
        passengers_go_up = [passenger for passenger in self.queues[el.current_floor]
                            if passenger.dest > el.current_floor]
        passengers_go_down = [passenger for passenger in self.queues[el.current_floor]
                              if passenger.dest < el.current_floor]

        if el.get_status() == Status.IDLE:
            if len(passengers_go_up) > 0:
                passengers_to_load = passengers_go_up
                el.dest = max(passenger.dest for passenger in passengers_go_up)
            elif len(passengers_go_down) > 0:
                passengers_to_load = passengers_go_down
                el.dest = min(passenger.dest for passenger in passengers_go_down)

        elif el.get_status() == Status.UP:
            passengers_to_load = passengers_go_up
            if len(passengers_to_load) > 0:
                el.dest = max(passenger.dest for passenger in passengers_to_load)

        elif el.get_status() == Status.DOWN:
            passengers_to_load = passengers_go_down
            if len(passengers_to_load) > 0:
                el.dest = min(passenger.dest for passenger in passengers_to_load)

        el.passengers += passengers_to_load

        self.queues[el.current_floor] = [passenger for passenger in self.queues[el.current_floor]
                                         if passenger not in passengers_to_load]

        if len(passengers_to_load) > 0:
            print("Elevator [{}] loaded [{}] passengers on floor [{}], destination is [{}]"
                  .format(eid, len(passengers_to_load), el.current_floor, el.dest))


class Passenger:
    dest = -1

    def __init__(self, dest, pid):
        self.dest = dest
        self.pid = pid


class Elevator:
    passengers = []
    current_floor = 1
    dest = 1

    def __init__(self, eid):
        self.eid = eid

    def get_status(self):
        if self.current_floor > self.dest:
            return Status.DOWN
        elif self.current_floor < self.dest:
            return Status.UP
        else:
            return Status.IDLE


class Status(Enum):
    UP = 1
    DOWN = 2
    IDLE = 3


if __name__ == "__main__":
    self = El()

    rt = threading.Thread(target=self.run)
    rt.start()

