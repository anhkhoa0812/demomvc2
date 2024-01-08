/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.utils;

/**
 *
 * @author tahoa
 */
public class MyApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastNameController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String START_UP_CONTROLLER = "startUpController";
        public static final String ADD_TO_CART_CONTROLLER = "addToCartController";
        public static final String LOGOUT_CONTROLLER = "logoutController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "removeItemsFromCartController";
        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
    }

    public class LoginFeature {

        public static final String SEARCH_PAGE = "searchPage";
        public static final String INVALID_PAGE = "invalidPage";
    }

    public class AddItemToCartFeature {

        public static final String BOOKSTORE_CONTROLLER = "bookStoreController";
        public static final String ERROR_NUMBERFORMAT = "Please input a digit";
    }

    public class BookStoreFeature {

        public static final String BOOKSTORE_PAGE = "bookStorePage";
    }

    public class CreateAccountFeature {

        public static final String CREATE_ERROR_PAGE = "createErrorPage";
        public static final String LOGIN_PAGE = "loginPage";
    }

    public class DeleteFeature {

        public static final String ERROR_PAGE = "errorOccursPage";
    }

    public class LogoutFeature {

        public static final String LOGIN_PAGE = "loginPage";
    }

    public class SearchLastNameFeature {

        public static final String SEARCH_PAGE = "searchPage";
    }

    public class StarUpFeature {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String SEARCH_PAGE = "searchPage";
    }

    public class UpdateAccountFeature {

        public static final String ERROR_PAGE = "errorOccursPage";
        public static final String PASSWORD_LENGTH_ERROR = "Password is required typing from 6 to 20 characters";
        public static final String SUCCESS_NOTI="Update successfully";
    }

    public class CartDispatchFeature {

        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "removeItemsFromCartController";
        public static final String CHECK_OUT_CONTROLLER = "checkOutServlet";
    }

    public class CheckOutFeature {

        public static final String ERROR_PAGE = "errorOccursPage";
        public static final String SHOW_CHECK_OUT_CONTROLLER = "showCheckOutController";
    }

    public class ShowCheckOutFeature {

        public static final String CHECK_OUT_PAGE = "checkOutPage";
    }

    public class RegistrationErrorFeature {

        public static final String USER_LENGTH_ERROR = "Username is required typing from 6 to 30 characters";
        public static final String PASSWORD_LENGTH_ERROR = "Password is required typing from 6 to 20 characters";
        public static final String CONFIRM_ERROR = "Confirm must be match Password";
        public static final String FULLNAME_LENGTH_ERROR = "Full name is required typing from 2 to 50 characters";
        public static final String USER_EXISTED_ERROR = " Username have existed";
    }
}
