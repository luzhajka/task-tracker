package com.luzhajka.tasktracker.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesUtil {

    public static String getMessageForLocale(String messageKey) {
        return ResourceBundle.getBundle("messages", Locale.getDefault())
                .getString(messageKey);
    }
}
