// FOLDER: controller/menu | MenuItem.java

package practicalprojectexample.controller.menu;

public enum MenuItem {
    ADD_VET(1, "Add vet"),
    UPDATE_VET(2, "Update vet"),
    DELETE_VET(3, "Delete vet"),
    VIEW_VET_LIST(4, "View vet list"),
    EXIT(100, "Exit"),
    UNKNOWN(999, "Unknown option");

    // We don't want the user to see the above naming,
    // so we'll define private final variables, with option number and name:
    private final int option;
    private final String displayName;

    MenuItem(int option, String displayName) {
        this.option = option;
        this.displayName = displayName;
    }

    public static void printMenuItems() {
        for (MenuItem value : values()) {
            if (value != UNKNOWN) {
                System.out.println(value.option + " --> " + value.displayName);
            }
        }
    }

    public static MenuItem searchByOption(int inputOption) {
        for (MenuItem value : values()) {
            if (value.option == inputOption) {
                return value;
            }
        }
        return UNKNOWN;
    }

}
