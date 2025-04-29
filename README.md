App thread          ConsumerContainer       ConsumerWorker          KafkaConsumer          YourHandler
    | startConsumers()  |                       |                       |                     |
    |------------------>|                       |                       |                     |
    |                   | findHandlers()        |                       |                     |
    |                   |---------------------->|                       |                     |
    |                   |                       |  new ConsumerWorker() |                     |
    |                   |                       |---------------------->|  subscribe()        |
    |                   |                       |                       |-------------------->|
    |                   | executor.submit()     |                       |                     |
    |                   |---------------------->| run() loop            |                     |
    |                   |                       |                       | poll()              |
    |                   |                       |                       |<--------------------|
    |                   |                       |  deserialize          |                     |
    |                   |                       |---------------------->|                     |
    |                   |                       |  invoke(handler)      |                     |
    |                   |                       |--------------------------------------------->|
    |                   |                       | commitSync()          |                     |
