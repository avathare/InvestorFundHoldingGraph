# Investor Holding Graph

## Problem statement
- Create graph which will show the relationship between Investor, Fund and Holding.
- Calculate Fund holding ana Investor Holding.
##Setup Project
step for setup application on local system
1) download code and open in IntelliJ IDEA or any standard IDE.
2) java 11 must be installed on system.
3) extract and import as maven project in IDE.
4) start main class TestingApplication

# User Guid
  - Create Vertex like Investor, Fund, Holding
  - use end point /addVertex or /addBulkVertex. To create vertex need two important attribute 1) name  2) type. if vertex is holding please provide value also
  - fetch created vertex use /getAllVertex endpoint.
  - To create Association use /addAssociation or /addBulkAssociation. Need fundid,invid and holdingid for create association.
  - To calculate fund holding use endpoint /getHolding/fund/{fundId}. will calculate holding value. if you want to exclude the any holding mention in request body will be excluded.
  - To calculate Investor Holding use endpoint /getHolding/investor/{investorId}. will calculate holding value with different fund and holdings.
