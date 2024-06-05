package ir.khodam;

import java.util.Iterator;
import java.util.regex.Pattern;

public class PhoneProcessor implements StringTemplate.Processor<String, IllegalArgumentException> {

    public static void main(String[] args) {
        PhoneProcessor pp = new PhoneProcessor();
        String workPhone = "072-825-9009";
        String homePhone = "(040)234-9670";
//        String message = pp.process("""
//You can contact me at work at \{workPhone}
//or at home at \{homePhone}.
//""");
    }

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");

    @Override
    public String process(StringTemplate stringTemplate) {

        StringBuilder sb = new StringBuilder();
        Iterator<String> fragmentsIter
                = stringTemplate.fragments().iterator();
        for (
                Object value : stringTemplate.values()) {
            sb.append(fragmentsIter.next());
            if (!PHONE_PATTERN.matcher(
                    (CharSequence) value).matches()) {
                throw new IllegalArgumentException(
                        "This is not a valid phone number");
            }
            sb.append(value);
        }
        sb.append(fragmentsIter.next());
        return sb.toString();
    }
}