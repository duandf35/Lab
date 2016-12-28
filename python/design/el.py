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

    highest_floor_to_load = -1
    lowest_floor_to_load = -1

    def __init__(self, nf, ne, gp, ce):
        """

        :param nf: nf number of floors
        :param ne: ne number of elevators
        :param gp: gp the period of generating new passenger
        :param ce: ce the period of operating elevator
        """
        self.nf = 6
        self.ne = 2
        self.gp = 0.2
        self.ce = 0.5

        if nf > 1:
            self.nf = nf

        if ne > 1:
            self.ne = ne

        if gp > 0:
            self.gp = gp

        if ce > 0:
            self.ce = ce

        for floor in range(1, nf + 1):
            self.queues[floor] = []

        for eid in range(1, ne + 1):
            self.elevators[eid] = Elevator(eid)

    def run(self):
        print("Start elevator threads...")

        self.new_passenger()

        for eid, el in self.elevators.items():
            self.operate_elevator(eid)

    def new_passenger(self):
        floor = random.randint(1, self.nf)
        dest = random.randint(1, self.nf)
        while dest == floor:
            dest = random.randint(1, self.nf)

        print("Created new passenger on floor [{}] with id [{}] and destination [{}]".format(floor, self.pc, dest))

        if floor < self.lowest_floor_to_load or self.lowest_floor_to_load == -1:
            self.lowest_floor_to_load = floor
        elif floor > self.highest_floor_to_load or self.highest_floor_to_load == -1:
            self.highest_floor_to_load = floor

        self.queues[floor].append(Passenger(dest, self.pc))
        self.pc += 1

        threading.Timer(interval=self.gp, function=self.new_passenger).start()

    def operate_elevator(self, eid):
        if self.elevators[eid].get_status() == Status.IDLE:
            if self.highest_floor_to_load != -1:
                self.elevators[eid].dest = self.highest_floor_to_load
                self.highest_floor_to_load = -1
            elif self.lowest_floor_to_load != -1:
                self.elevators[eid].dest = self.lowest_floor_to_load
                self.lowest_floor_to_load = -1

        self.load_passenger(eid)
        self.move_elevator(eid)

        threading.Timer(interval=self.ce, function=self.operate_elevator, args=(eid,)).start()

    def move_elevator(self, eid):
        el = self.elevators[eid]
        from_floor = el.current_floor

        if el.get_status() == Status.UP:
            el.current_floor += 1

        elif el.get_status() == Status.DOWN:
            el.current_floor -= 1

        el.passengers = [passenger for passenger in el.passengers if passenger.dest != el.current_floor]
        time.sleep(0.5)

        if from_floor != el.current_floor:
            print("Elevator [{}] moved to from floor [{}] to floor [{}]".format(eid, from_floor, el.current_floor))
        else:
            print("Elevator [{}] is idle on floor [{}]".format(eid, el.current_floor))

    def load_passenger(self, eid):
        el = self.elevators[eid]
        passengers_to_load = []
        new_dest = el.current_floor

        if el.get_status() == Status.IDLE or el.get_status() == Status.UP:
            passengers_to_load = [passenger for passenger in self.queues[el.current_floor]
                                  if passenger.dest > el.current_floor]
            if len(passengers_to_load) > 0:
                new_dest = max(passenger.dest for passenger in passengers_to_load)

        elif el.get_status() == Status.DOWN:
            passengers_to_load = [passenger for passenger in self.queues[el.current_floor]
                                  if passenger.dest < el.current_floor]
            if len(passengers_to_load) > 0:
                new_dest = min(passenger.dest for passenger in passengers_to_load)

        el.passengers += passengers_to_load
        el.dest = new_dest

        self.queues[el.current_floor] = [passenger for passenger in self.queues[el.current_floor]
                                         if passenger not in passengers_to_load]

        print("Elevator [{}] loaded [{}] passengers on floor [{}], new destination is [{}]"
              .format(eid, len(passengers_to_load), el.current_floor, new_dest))


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
    self = El(6, 2, 1, 0.5)

    rt = threading.Thread(target=self.run)
    rt.start()

