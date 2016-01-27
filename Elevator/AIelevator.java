
public class AIelevator extends GenericElevator {
		
	public void move(Building building){                        // will actually move the elevator
		  checkDirection(building);                             // checks to see if any one is waiting in the direction
		  moveControl(building);                                // sees if anyone is waiting at all
		  if (getDirection() == true ){                         // checks direction
			   if (getElevatorFloor() <= building.getFloors()){ // makes sure not to exceed
				   setElevatorFloor(getElevatorFloor() + 1);    // sets new floor
			       }
			   else {setElevatorDirection(false);               // if it does exceed it flips direction
		           }
		    }
		if (getDirection() == false){                           // checks direction
			   if (getElevatorFloor() - 1 >= 1){                // makes sure not to exceed
			       setElevatorFloor(getElevatorFloor() - 1);    // sets new floor
	               }
			   else{ setElevatorDirection(true);                // if it does exceed it flips direction
	               }
		    }
	   }
	private void checkDirection(Building building) {
		int flag = 0;                                           // indicates a call is made that needs an elevator
		if (getDirection() == true){                            // if elevator is going up
			for(int i = getElevatorFloor();
					i <= building.getFloors();i++){             // sets i to elevator floors and loops to top floor
				for(int j = 0;
						j <= building.getCallLength()-1; j++ ){ // runs through active calls 
				if(building.getActiveCallPosition(j)
						.getFromFloor()== i){                   //finds match
					flag++;                                     // increases flag and breaks
					break;                                      // breaks no need to search more
				}
			}
			  for(int k = 0; k < getCapacity(); k++ ){          // loops through capacity of elevator 
				if (getCallsInside(k).getToFloor()== i){        //finds match
					flag++;                                     // increases count and breaks
					break;                                      // breaks no need to search more
				}
			  }
			  if(flag > 0){                                     // if flag was reached breaks loop
					break;                                      // breaks so direction is not reversed
				}
			}
			if(flag <= 0){                                      // if count was not reached flips direction
				setElevatorDirection(false);                    // reverses direction
			}
		  }
		else if (getDirection() == false){                      // if elevator is going down
			for(int i = getElevatorFloor(); i >= 1;i--){
				for(int j = 0;
						j <= building.getCallLength()-1; j++ ){ // sets j elevator position and loops to botoom floor
					if(building.getActiveCallPosition(j)
							.getFromFloor()== i){               //finds match
					flag++;                                     // increases flag and breaks
					break;                                      // breaks no need to search more
				}
			}
			  for(int k = 0; k < getCapacity (); k++ ){         // loops through capacity of elevator 
				if (getCallsInside(k).getToFloor()== i){        //finds match
					flag++;                                     // increases count and breaks
					break;                                      // breaks no need to search more
				}
			  }
				if(flag > 0){                                   // if flag was reached breaks loop
					break;                                      // breaks so direction is not reversed
				}
				}
			if(flag <= 0){                                      // if count was not reached flips direction
				setElevatorDirection(true);                     // reverses direction
			}
		}
	}
  
	 void moveControl(Building building){
			 boolean noOutsideCalls = false;                                    // holds boolean for outside Calls
			 boolean noInsideCalls = false;                                     // holds boolean for inside Calls
			 for(int j = 0; j <= building.getCallLength()-1; j++ ){             // runs through active calls 
					if(building.getActiveCallPosition(j).getFromFloor() > -1){  //finds match since -1 was the empty indicator
						noOutsideCalls = false;	                                // sets it false since an instance has been found
						break;                                                  // breaks out so the false sticks 
					   }
					noOutsideCalls = true;                                      // sets true if nothing is found
					}
			 for(int k = 0; k < getCapacity(); k++ ){                           // loops through capacity of elevator 
					if (getCallsInside(k).getToFloor() >-1){                    // finds match
						noInsideCalls = false;                                  // sets it false since an instance has been found
						break;                                                  // breaks out so the false sticks 
					}
					noInsideCalls = true;                                       // sets true if nothing is found
			 }
		if(noInsideCalls==true && noOutsideCalls==true ){                       // makes sure there is no calls
			  if(getElevatorFloor()  <  getRestingPoint()){                     // if resting point is up
				  setElevatorDirection(true);                                   // sets the direction up
			     }
			  if(getElevatorFloor()  >  getRestingPoint()){                     // if resting point is down
				  setElevatorDirection(false);                                  // sets the direction down
			    }
			 }
	     }
	
    }

		
	
