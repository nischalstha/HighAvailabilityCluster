# HighAvailabilityCluster

design and implementation of a simple application layer protocol over UDP to facilitate High Availability Cluster (HAC).
<br />
HAC has a set of mechanism to detect failovers, network/node failure etc. in order to re-route the traffic to the available systems. 
<br />
The designed protocol will perform the following functions:
- To detect node failure periodically
- To inform the other nodes in the network about the failure (peering option)
- To be able to detect when the failed node comes back to life
- To inform other nodes about the availability of new node
<br />

## The protocol is designed to run in both Peer to Peer and Client-Server mode of HAC.
