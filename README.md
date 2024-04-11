# An Interactive Smart-Home

## Turning corporate invasion of privacy into a game!




Ignoring the critical title, this application will simulate an environment where a user can 
control the electrical appliances and lights in their fancy smart home. There is a growing user base 
for applications such as these, as we are moving towards a much more connected world. Essentially, anyone
living in a house/apartment with internet connected appliances will be the target audience. I am interested in this project 
because it both seems like a practical project that isn't completely *useless*, so to speak, and because
the project itself can be refactored later on for actual use.

 ## **USER STORIES**:
- As a User, I want to add rooms to my house
- As a User, I want to categorize my rooms
- As a User, I want to add appliances and lights to my rooms
- As a User, I want to see every appliance turned on in my house
- As a User, I want to see how much power my house is using
- As a User, I want to be able to save my house to file (if I so choose)
- As a User, I want to be able to load my house from file (if I so choose)



## **INSTRUCTION FOR GRADER**:
- You can generate the first required action relating to the user story 
  - *I want to add rooms to my house*
  - by navigating to the 'My Home' tab, filling out the required fields : "Name of Room", and "Number of LightBulbs"
  - and pressing the button 'Add Room'
  - You can see the added rooms by clicking 'Check for newly loaded rooms'

- If rooms have been added, you can generate the second required action relating to the user story
  - *I want to add appliances to my room*
  - by clicking on a specific room tab, filling out the required fields : "Name", and "Energy Usage (kWh)", and clicking add appliance
  - You can see the added appliances by navigating to the  'House Stats' tab, and pressing the "House Statistics" button
- You can locate my visual component by looking at the 'Menu' screen
- You can save the state of my application by pressing "Save" in the "Menu" tab
- You can reload the state of my application by pressing "Load" in the "Menu" tab

## **Phase 4: Task 2** ##
Wed Apr 03 17:09:52 PDT 2024
Added 12 lightbulbs to room: living room

Wed Apr 03 17:09:52 PDT 2024
Added room: living room

Wed Apr 03 17:10:02 PDT 2024
Added appliance: tv to room: living room

Wed Apr 03 17:10:07 PDT 2024
Added 7 lightbulbs to room: kitchen

Wed Apr 03 17:10:07 PDT 2024
Added room: kitchen

Wed Apr 03 17:10:21 PDT 2024
Added appliance: microwave to room: kitchen

Wed Apr 03 17:10:26 PDT 2024
Saved Rooms, and Appliances to: my home


## **Phase 4: Task 3** ##
If I had more time to work on the project, one of the things that I would do to refactor the program would be to 
implement the singleton pattern for the house. Inherently, one would only use this application for a singular house
(see the name single), and this would ensure that one doesn't accidentally create duplicates, and ensures that many 
issues do not arise.