# HighAvailabilityCluster

design and implementation of a simple application layer protocol over UDP to facilitate High Availability Cluster (HAC)
HAC has a set of mechanism to detect failovers, network/node failure etc. in order to re-route the traffic to the available systems. 
The designed protocol will perform the following functions:
a) To detect node failure periodically
b) To inform the other nodes in the network about the failure (peering option)
c) To be able to detect when the failed node comes back to life
d) To inform other nodes about the availability of new node

# The protocl is designed to run in both Peer to Peer and Client-Server mode of HAC.
