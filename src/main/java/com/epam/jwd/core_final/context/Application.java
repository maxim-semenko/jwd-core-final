package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

public interface Application {

    static void start() {

        // load properties for application
        PropertyReaderUtil.loadProperties();

        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance; // todo
        NassaContext.getInstance().init();

        NassaContext.LOGGER.info("The application was launched");
        handleMenu(applicationContextSupplier::get);
    }


    private static void handleMenu(ApplicationMenu applicationMenu) {
        Scanner in = new Scanner(System.in);
        int choose = -1;
        while (choose != 0) {
            applicationMenu.printAvailableOptions();
            try {
                choose = in.nextInt();
                applicationMenu.handleUserInput(choose);
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.println("You input incorrect data! Try again.");
                NassaContext.LOGGER.error("User input incorrect data");
            } catch (InvalidStateException e) {
                System.out.println("Error");
            }
        }
    }


}
