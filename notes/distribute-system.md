### Notification System
https://www.youtube.com/watch?v=s3GfXTnzG_Y
* scheduled delivery
* event triggered delivery
* retry delivery

* complete in seconds
* ordering
* idempotent
* failure

#### Old Solution: task queue (wide row queue in cassandra) + thread pool
 - q: how to distribute to multiple instances without duplication? 
 - a: multiple partitions for multiple instances
 - p: hard to add/remove instances, slow I/O, complex

#### New Solution: kafka + cassandra + akka
- kafka for distributing task metadata
- cassandra for persisting task
- dynamic load: 
    - kafka broker (leader + follower) -> partitions
    - cassandra node -> token range (which row to go)
- datacenter outage: kafka brokers can re-pick leaders when leaders go down & redistribute tasks
    - (kafka) split brokers into different data centers
    - (cassandra) multiple replicas per partition, one in each datacenter
    - (cassandra) quorum writes replicated to >= 2 data centers (may be slow)
    - (cassandra) quorum reads latest written value
- ordering:
    - hash(task.orderingId) % # of partitions => lead related tasks (logic queue) go to the same partition (same machine)
    - (akka) multiple short-live actors to execute (retry) one task from the logic queue
- idempotency:
    - maintain task state in cassandra
- testing:
    - controlled cutting communication/killing node in production

### Distributed Locking
https://www.youtube.com/watch?v=MDuagr729aU
#### Ring Pop
  * gossip protocol: propagate cluster info
  * consistent hashing: re-balance cluster nodes
  * forward req to the owner worker
    - pros:
      a. no external resource required
    - cons:
      a. detour in req forwarding
      b. hotspot
      c. re-balancing delay cause one trip may be proceed by multiple workers
      d. network partition cause one trip may be proceed by multiple workers in different network
      e. gossip protocol has scalability limitation
        - solution: sub-sharding
#### DLM
  * non-state-owning services
  * locking leasing protocol
  * cassandra for lock persistence
  * helix for cluster management
  * centralize management:
   - pros:
    a. solve 1.c issue
   - cons:
    a. external resource required
    b. lock persisting is not scale well
      - solution: in-memory locks but lose all lock state when failure happens

#### The Raft Consensus Algorithm (Future)

### Cassandra memtable
https://docs.datastax.com/en/archived/cassandra/3.0/cassandra/dml/dmlHowDataWritten.html#dmlHowDataWritten__logging-writes-and-memtable-storage
* cache writing strategies: https://shahriar.svbtle.com/Understanding-writethrough-writearound-and-writeback-caching-with-python
