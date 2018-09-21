## AdoptCD.M
The following design address the adoption curve of a derivaties ledger in a new decentralized paradigm.

* Layer 0 - Distributed Ledger / Store of events
A trusted messaging service and a log of events. A store of Provenance.  A standardization layer, a store of metadata. Access Control Layer.

* Layer 1 - A Standard Event Interpretation layer
An event interepretation layer that provides a transparent set of state transfer functions.  The design of this layer is setup to drive the adpotion curve.

    * Hack 1 - A standard event store with event versioning. (This is a well solved problem and can be either delegated to the DLT of choice).
        * Goals - All market participants can consume and produce their events into Layer 0.  All interactions with Layer 0 are immutable and the DTL of choice or some other subsystem guarantees it manages a WAL of all messages.

    * Hack 2 - A event transition store. 
        * Goals - Support on-chain and off-chain representations of event transitions.
        * Goals - As the ISDA CDM event transitions are formally defined, we can represent all these events as pure functions we store on the chain. This allows for off-chain computation and validation of the transactions.


* Layer 2 - Integration Layers to existing Post Trade Processing Systems 

    * Goals - Existing trade processing systems must be able to Consume raw events
    * Goals - Existing trade processing systems must be able to recompute trade events, for (simulation and backtesting usecases)
    * Goals - DLT portability. A bank or participant must be able to freely transfer from one DLT implementation to another with the design of the given interfaces     

