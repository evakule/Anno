import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Encryption {
    private static final String AT = "@";

    private static final String IGT_TEST_COM = "igt-test.com";

    private static final String ABC
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";

    private static final String DIGITS
            = "1234567890";

    private static final String LAST_NAME_SUBSTITUTION_STRATEGY
            = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba987654321";

    private static final String FIRST_NAME_SUBSTITUTION_STRATEGY
            = "EKFJACZVIDBLMNORQPSTUHWXYGekfjaczvidblmnorqpstuhwxyg123456789";

    private static final String DATE_OF_BIRTH_SUBSTITUTION_STRATEGY
            = "01.02.1970 " +
            "29.02.1984 " +
            "02.03.2002 " +
            "03.04.1960 " +
            "04.05.1970 " +
            "05.06.1980 " +
            "06.07.1970 " +
            "07.08.1990 " +
            "08.09.1970 " +
            "09.10.1980";

    private static final String SSN4_SUBSTITUTION_STRATEGY
            = "7328594961";

    private static final String ADDR_LINE1_SUBSTITUTION_STRATEGY
            = "XOZACEUTLNMVWDHFPRQKBISYJGxozaceutlnmvwdhfprqkbisyjg123789456";

    private static final String ADDR_LINE2_SUBSTITUTION_STRATEGY
            = "WRZEXSKVMNTQLPFGDJUBACHOYIwrzexskvmntqlpfgdjubachoyi345678912";

    private static final String CITY_SUBSTITUTION_STRATEGY
            = "NIJEKMWQDORSGXFYTZUAVCLPHBnijekmwqdorsgxfytzuavclphb456123789";

    private static final String US_SHORT_FORM_STATE_CODES
            = "AL AK AZ AR CA CO CT DE DC FL GA HI ID IL IN IA KS KY LA ME MD " +
            "MA MI MN MS MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN " +
            "TX UT VT VA WA WV WI WY";

    private static final String US_SHORT_FORM_STATE_CODES_SUBSTITUTION_STRATEGY
            = "WA WA WA WA WA WA WA NM NM NM NM NM NM NM HI HI HI HI HI HI MT " +
            "MT MT MT MT MT MT MI AL AL AL AL MI MS MS MS MS MS MS TX TX TX NH " +
            "NH NH NH SD SD SD SD SD";

    private static final String KEYS_POST_CODE_SUBSTITUTION_STRATEGY
            = "WA NM HI MT MI AL MS TX NH SD";

    private static final String VALUES_POST_CODE_SUBSTITUTION_STRATEGY
            = "98001 87103 96815 59101 48001 35010 39553 73301 3033 57002";

    private static final String E_MAIL_SUBSTITUTION_STRATEGY
            = "LOUFKTCVEPGWYSJXQZRHABDNIMloufktcvepgwysjxqzrhabdnim179348625";

    private static final String PRIMARY_PHONE_SUBSTITUTION_STRATEGY
            = "9874506123";

    private static final String SECONDARY_PHONE_SUBSTITUTION_STRATEGY
            = "6497132508";

    private final HashMap<String, String> lastNamePair = new HashMap<>();
    private final HashMap<String, String> firstNamePair = new HashMap<>();
    private final HashMap<String, String> dateOfBirthPair = new HashMap<>();
    private final HashMap<String, String> ssn4Pair = new HashMap<>();
    private final HashMap<String, String> addrLine1Pair = new HashMap<>();
    private final HashMap<String, String> addrLine2Pair = new HashMap<>();
    private final HashMap<String, String> cityPair = new HashMap<>();
    private final HashMap<String, String> usShortFormStateCodesPair = new HashMap<>();
    private final HashMap<String, String> postCodePair = new HashMap<>();
    private final HashMap<String, String> emailPair = new HashMap<>();
    private final HashMap<String, String> primaryPhonePair = new HashMap<>();
    private final HashMap<String, String> secondaryPhonePair = new HashMap<>();

    private final SsnProcess ssnProcess;

    public Encryption() {
        initLastNameSubstitutionStrategy();
        initFirstNameSubstitutionStrategy();
        initDateSubstitutionStrategy();
        initSsn4SubstitutionStrategy();
        initAddrLine1SubstitutionStrategy();
        initAddrLine2SubstitutionStrategy();
        initCitySubstitutionStrategy();
        initUSShortFormStateCodeSubstitutionStrategy();
        initPostCodeSubstitutionStrategy();
        initEmailSubstitutionStrategy();
        initPrimaryPhoneSubstitutionStrategy();
        initSecondaryPhoneSubstitutionStrategy();
        ssnProcess = new SsnProcess();
    }

    private void initLastNameSubstitutionStrategy() {
        for (int i = 0; i < ABC.length(); i++) {
            Character key = ABC.charAt(i);
            Character value = LAST_NAME_SUBSTITUTION_STRATEGY.charAt(i);
            lastNamePair.put(key.toString(), value.toString());
        }
    }

    private void initFirstNameSubstitutionStrategy() {
        for (int i = 0; i < ABC.length(); i++) {
            Character key = ABC.charAt(i);
            Character value = FIRST_NAME_SUBSTITUTION_STRATEGY.charAt(i);
            firstNamePair.put(key.toString(), value.toString());
        }
    }

    private void initDateSubstitutionStrategy() {
        String[] arrayOfDates = DATE_OF_BIRTH_SUBSTITUTION_STRATEGY.split(" ");
        for (int i = 0; i < arrayOfDates.length; i++) {
            String key = String.valueOf(i);
            dateOfBirthPair.put(key, arrayOfDates[i]);
        }
    }

    private void initSsn4SubstitutionStrategy() {
        int length = SSN4_SUBSTITUTION_STRATEGY.length();
        for (int i = 0; i < length; i++) {
            Integer key = i;
            Character value = SSN4_SUBSTITUTION_STRATEGY.charAt(i);
            ssn4Pair.put(key.toString(), value.toString());
        }
    }

    private void initAddrLine1SubstitutionStrategy() {
        for (int i = 0; i < ABC.length(); i++) {
            Character key = ABC.charAt(i);
            Character value = ADDR_LINE1_SUBSTITUTION_STRATEGY.charAt(i);
            addrLine1Pair.put(key.toString(), value.toString());
        }
    }

    private void initAddrLine2SubstitutionStrategy() {
        for (int i = 0; i < ABC.length(); i++) {
            Character key = ABC.charAt(i);
            Character value = ADDR_LINE2_SUBSTITUTION_STRATEGY.charAt(i);
            addrLine2Pair.put(key.toString(), value.toString());
        }
    }

    private void initCitySubstitutionStrategy() {
        for (int i = 0; i < ABC.length(); i++) {
            Character key = ABC.charAt(i);
            Character value = CITY_SUBSTITUTION_STRATEGY.charAt(i);
            cityPair.put(key.toString(), value.toString());
        }
    }

    private void initUSShortFormStateCodeSubstitutionStrategy() {
        String[] originalCodes = US_SHORT_FORM_STATE_CODES.split(" ");
        String[] toReplace = US_SHORT_FORM_STATE_CODES_SUBSTITUTION_STRATEGY.split(" ");
        for (int i = 0; i < originalCodes.length; i++) {
            usShortFormStateCodesPair.put(originalCodes[i], toReplace[i]);
        }
    }

    private void initPostCodeSubstitutionStrategy() {
        String[] key = KEYS_POST_CODE_SUBSTITUTION_STRATEGY.split(" ");
        String[] value = VALUES_POST_CODE_SUBSTITUTION_STRATEGY.split(" ");
        for (int i = 0; i < key.length; i++) {
            postCodePair.put(key[i], value[i]);
        }
    }

    private void initEmailSubstitutionStrategy() {
        for (int i = 0; i < ABC.length(); i++) {
            Character key = ABC.charAt(i);
            Character value = E_MAIL_SUBSTITUTION_STRATEGY.charAt(i);
            emailPair.put(key.toString(), value.toString());
        }
    }

    private void initPrimaryPhoneSubstitutionStrategy() {
        for (int i = 0; i < PRIMARY_PHONE_SUBSTITUTION_STRATEGY.length(); i++) {
            Character key = DIGITS.charAt(i);
            Character value = PRIMARY_PHONE_SUBSTITUTION_STRATEGY.charAt(i);
            primaryPhonePair.put(key.toString(), value.toString());
        }
    }

    private void initSecondaryPhoneSubstitutionStrategy() {
        for (int i = 0; i < PRIMARY_PHONE_SUBSTITUTION_STRATEGY.length(); i++) {
            Character key = DIGITS.charAt(i);
            Character value = SECONDARY_PHONE_SUBSTITUTION_STRATEGY.charAt(i);
            secondaryPhonePair.put(key.toString(), value.toString());
        }
    }

    private String replaceSymbols(String row, HashMap<String, String> pair) {
        if (row != null) {
            char[] charArray = row.toCharArray();
            for (int i = 0; i < row.length(); i++) {
                Character toReplace = charArray[i];
                String replacement = pair.get(toReplace.toString());
                if (replacement != null) {
                    charArray[i] = replacement.charAt(0);
                }
            }
            return new String(charArray);
        }
        return "null";
    }

    private String replaceStates(String state, HashMap<String, String> pair) {
        return pair.get(state);
    }

    private String hideLastName(String lastName) {
        return replaceSymbols(lastName, lastNamePair);
    }

    private String hideFirstName(String firstName) {
        return replaceSymbols(firstName, firstNamePair);
    }

    private String hideDateOfBirth(String dateOfBirth) {
        char key = dateOfBirth.charAt(1);
        return dateOfBirthPair.get(Character.toString(key));
    }

    private String hideSsn4(String hashedSsn4) {
        String decodedSsn4 = ssnProcess.getSsnList().get(hashedSsn4);
        String newSsn4 = replaceSymbols(decodedSsn4, ssn4Pair);
        if (!newSsn4.equals("null"))
            return Hashing.sha1().hashString(newSsn4, StandardCharsets.UTF_8).toString();
        else
            return "null";

    }

    private String hideAddrLine1(String addrLine1) {
        return replaceSymbols(addrLine1, addrLine1Pair);
    }

    private String hideAddrLine2(String addrLine2) {
        return replaceSymbols(addrLine2, addrLine2Pair);
    }

    private String hideCity(String city) {
        return replaceSymbols(city, cityPair);
    }

    private String hideUsStateShortFormCode(String usStateShortForm) {
        return replaceStates(usStateShortForm, usShortFormStateCodesPair);
    }

    private String hidePostCode(String usStateShortForm) {
        return postCodePair.get(usStateShortForm);
    }

    private String hideEMail(String email) {
        String updated = replaceSymbols(email, emailPair);
        if (updated.contains(AT)) {
            int atIndex = updated.indexOf(AT);
            String emailBody = updated.substring(0, atIndex);
            updated = emailBody + AT + IGT_TEST_COM;
        }
        return updated;
    }

    private String hidePrimaryPhone(String primaryPhone) {
        return replaceSymbols(primaryPhone, primaryPhonePair);
    }

    private String hideSecondaryPhone(String secondaryPhone) {
        return replaceSymbols(secondaryPhone, secondaryPhonePair);
    }

    public String[] getChangedRow(String[] rowColumns) {
        String[] newRowColumns = rowColumns;
        if (newRowColumns.length > 1 && !newRowColumns[1].isEmpty()) {
            newRowColumns[1] = hideLastName(newRowColumns[1]);
        }
        if (newRowColumns.length > 2 && !newRowColumns[2].isEmpty()) {
            newRowColumns[2] = hideFirstName(newRowColumns[2]);
        }
        if (newRowColumns.length > 3 && !newRowColumns[3].isEmpty()) {
            newRowColumns[3] = hideDateOfBirth(newRowColumns[3]);
        }
        if (newRowColumns.length > 4 && !newRowColumns[4].isEmpty()) {
            newRowColumns[4] = hideSsn4(newRowColumns[4]);
        }
        if (newRowColumns.length > 5 && !newRowColumns[5].isEmpty()) {
            newRowColumns[5] = hideAddrLine1(newRowColumns[5]);
        }
        if (newRowColumns.length > 6 && !newRowColumns[6].isEmpty()) {
            newRowColumns[6] = hideAddrLine2(newRowColumns[6]);
        }
        if (newRowColumns.length > 7 && !newRowColumns[7].isEmpty()) {
            newRowColumns[7] = hideCity(newRowColumns[7]);
        }
        if (newRowColumns.length > 8 && !newRowColumns[8].isEmpty()) {
            newRowColumns[8] = hideUsStateShortFormCode(newRowColumns[8]);
        }
        if (newRowColumns.length > 10 && !newRowColumns[10].isEmpty()) {
            newRowColumns[10] = hidePostCode(newRowColumns[8]);
        }
        if (newRowColumns.length > 11 && !newRowColumns[11].isEmpty()) {
            newRowColumns[11] = hideEMail(newRowColumns[11]);
        }
        if (newRowColumns.length > 14 && !newRowColumns[14].isEmpty()) {
            newRowColumns[14] = hidePrimaryPhone(newRowColumns[14]);
        }
        if (newRowColumns.length > 15 && !newRowColumns[15].isEmpty()) {
            newRowColumns[15] = hideSecondaryPhone(newRowColumns[15]);
        }
        return newRowColumns;
    }
}
