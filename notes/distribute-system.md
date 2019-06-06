* CAP theorem
    - cache
    - state recovery
    - service discovery
    - consensus (raft)
    - consistency
* idempotent
* sharding (Cassandra)
* distributed lock
* kafka
* supervision & monitoring
* cluster management
 - k8s
 - docker
 - ecs

## Notification system: https://www.youtube.com/watch?v=s3GfXTnzG_Y
* scheduled delivery
* event triggered delivery
* retry delivery

* complete in seconds
* ordering
* idempotent
* failure

### old sol: task queue (wide row queue in cassandra) + thread pool
 - q: how to distribute to mulitple instances without duplication? 
 - a: multiple partitions for multiple instances
 - p: hard to add/remove instances, slow I/O, complex

### new sol: kafka + cassandra + akka
- kafka for distributing task metadata
- cassandra for persisting task
- dynamic load: 
    - kafka broker (leader + follower) -> partitions
    - cassandra node -> token range (which row to go)
- datacenter outage: kafka brokers can repick leaders when leaders go down & redistribute tasks
    - (kafka) split brokers into different datacenters
    - (cassandra) multiple relicas per partition, one in each datacenter
    - (cassandra) quorum writes replicated to >= 2 datacenters (may be slow)
    - (cassandra) quorum reads latest written value
- ordering:
    - hash(task.orderingId) % # of partitions => lead related tasks (logic queue) go to the same partition (same machine)
    - (akka) multiple short-live actors to execute (retry) one task from the logic queue
- testing:
    - controlled cutting communication/killing node in production

## Distributed locking: https://www.youtube.com/watch?v=MDuagr729aU
### Ring Pop
  * Gossip protocol: propagate cluster info
  * Consistent hashing: rebalance cluster nodes
  * foward req to the owner worker
    - pros:
      a. no external resource required
    - cons:
      a. detour in req forwarding
      b. hotspot
      c. rebalancing delay cause one trip may be proceed by multiple workers
      d. network partition cause one trip may be proceed by multiple workers in different network
      e. Gossip protocol has scalability limitation
        - solution: sub-sharding
### DLM
  * Non-state-owning services
  * Locking leasing protocol
  * Cassandra for lock persistence
  * Helix for cluster management
  * centralize management:
   - pros:
    a. solve 1.c issue
   - cons:
    a. external resource required
    b. lock persisting is not scale well
      - solution: in-memory locks but lose all lock state when failure happens

### raft (Future)
