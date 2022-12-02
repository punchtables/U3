package controller;

//only imports what is strictly necessary from view-package
import view.CustomPizzaFrame;
import view.MainFrame;
import view.ButtonType;

public class Controller {
    private MainFrame view;
    private CustomPizzaFrame newPizzaType;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;
    private String [] foodMenuString; // for test purposes - delete if not used in final solution
    private String [] drinkMenuString; // for test purposes - delete if not used in final solution
    private String [] orderHistoryMenuString; // for test purposes - delete if not used in final solution
    private String [] order1Simulation; // for test purposes - delete if not used in final solution
    private String [] currentOrderArray; // for test purposes - delete if not used in final solution
    private double costCurrentOrder = 0; // for test purposes - delete if not used in final solution
    private int nbrOfOrders = 0; // for test purposes - delete if not used in final solution

    public Controller() {
        view = new MainFrame(1000, 500, this);
        loadStringTestValues(); //for test purposes - remove when not needed more
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }

    //This method is only used for test purposes - remove when no longer needed
    private void loadStringTestValues() {
        foodMenuString = new String[10];
        drinkMenuString = new String[10];
        orderHistoryMenuString = new String[10];
        order1Simulation = new String[10];
        currentOrderArray = new String[10];

        foodMenuString[0] = "mat0, topping1, topping2, matPris0";
        foodMenuString[1] = "mat1, topping1, topping3, matPris1";
        foodMenuString[2] = "mat2, topping1, topping2, matPris2";
        foodMenuString[3] = "mat3, topping1, topping3, matPris3";

        drinkMenuString[0] = "dryck1, dryckPris1";
        drinkMenuString[1] = "dryck2, alkohol %, dryckPris2";

        orderHistoryMenuString[0] = "order1: kostnad:100";
        orderHistoryMenuString[1] = "order2: kostand:200";

        order1Simulation[0] = "Order 1";
        order1Simulation[1] = "mat1 pris1";
        order1Simulation[2] = "mat2 pris2";
        order1Simulation[3] = "dryck1 pris3";

    }

    //This method is called by class MinFrame when a button in teh GUI is pressed
    public void buttonPressed(ButtonType button){

        switch (button) {
            case Add:
                addItemToOrder(view.getSelectionLeftPanel());
                break;

            case Food:
                setToFoodMenu();
                break;

            case Drinks:
                setToDrinkMenu();
                break;

            case MakePizza:
                addNewFood();
                break;

            case OrderHistory:
                setToOrderHistoryMenu();
                break;

            case Order:
                placeOrder();
                break;

            case ViewOrder:
                viewSelectedOrder(view.getSelectionLeftPanel());
                break;
        }
    }

    public void addItemToOrder(int selectionIndex) {
        System.out.println("Index selection left panel: " + selectionIndex); //for test purposes  - remove when not needed

        if (selectionIndex != -1){ // if something is selected in the left menu list
            switch (currentLeftMenu) { //This might need to change depending on architecture
                case Food:
                    currentOrderArray[nbrOfOrders] = foodMenuString[selectionIndex].toString(); //for test purposes - needs to be replaced with solution of finding chosen menu item matching architecture for model
                    break;
                case Drinks:
                    currentOrderArray[nbrOfOrders] = drinkMenuString[selectionIndex].toString(); //see comment for case above
                    break;
            }
            nbrOfOrders++; //for test purposes - need to be removed or changed when model for handling orders is implemented
            costCurrentOrder = costCurrentOrder + 100; //for test purposes - replace with calculation of cost when how orders are handled is implemented in model
            view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }

    }

    public void viewSelectedOrder(int selectionIndex){
        System.out.println("Index selection left panel: " + selectionIndex); //for test purposes  - remove when not needed

        if ((selectionIndex != -1) && currentLeftMenu==ButtonType.OrderHistory){
            costCurrentOrder = 100; //for test purposes - replace with calculation of cost when how orders are handled is implemented in model
            view.populateRightPanel(order1Simulation); //update left panel with order details - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }
    }


    public void setToFoodMenu() {
        currentLeftMenu = ButtonType.Food;
        view.populateLeftPanel(foodMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disableFoodMenuButton();
        view.disableViewSelectedOrderButton();
    }


    public void setToDrinkMenu() {
        currentLeftMenu = ButtonType.Drinks;
        view.populateLeftPanel(drinkMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disableDrinksMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToOrderHistoryMenu() {
        currentLeftMenu = ButtonType.OrderHistory;
        view.clearRightPanel();
        view.populateLeftPanel(orderHistoryMenuString);
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();
    }


    public void addNewFood() {
        newPizzaType = new CustomPizzaFrame(this);
        //For grade VG: Add more code to save the new Pizza type and update menu,
        view.enableAllButtons();
    }

    public void placeOrder() {
        System.out.println("Pressed Order to create a new order"); //for test purposes - remove when not needed more
        currentOrderArray = new String[10];  // for test purposes - remove when not needed more
        costCurrentOrder = 0;
        view.clearRightPanel(); //Removes information from right panel in GUI
        view.setTextCostLabelRightPanel("TOTAL COST: 0");
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }


}
