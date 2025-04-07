package JavaBasic;

import java.util.Scanner;

public class ContactBookUserInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();
        Contact contact = new Contact(name);

        System.out.print("Enter phone code (or -1 to skip): ");
        int code = scanner.nextInt();
        scanner.nextLine();
        if (code != -1) {
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            contact.addPhoneNumber(code, phoneNumber);
        }

        for (int i = 0; i < 3; i++) {
            System.out.print("Add email? (yes/no): ");
            String emailAns = scanner.nextLine();
            if (!emailAns.equalsIgnoreCase("yes")) break;

            System.out.print("Local part: ");
            String local = scanner.nextLine();
            System.out.print("Domain: ");
            String domain = scanner.nextLine();
            contact.addEmail(local, domain);
        }

        System.out.print("Add EPAM email? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("First name: ");
            String first = scanner.nextLine();
            System.out.print("Last name: ");
            String last = scanner.nextLine();
            contact.addEpamEmail(first, last);
        }

        for (int i = 0; i < 5; i++) {
            System.out.print("Add social media? (yes/no): ");
            String socialAns = scanner.nextLine();
            if (!socialAns.equalsIgnoreCase("yes")) break;

            System.out.print("Platform (Twitter, Instagram, Other): ");
            String platform = scanner.nextLine();

            if (platform.equalsIgnoreCase("Twitter")) {
                System.out.print("Twitter ID: ");
                contact.addTwitter(scanner.nextLine());
            } else if (platform.equalsIgnoreCase("Instagram")) {
                System.out.print("Instagram ID: ");
                contact.addInstagram(scanner.nextLine());
            } else {
                System.out.print("Custom Title: ");
                String title = scanner.nextLine();
                System.out.print("ID: ");
                contact.addSocialMedia(title, scanner.nextLine());
            }
        }

        System.out.println("\nContact Info:");
        for (Contact.ContactInfo info : contact.getInfo()) {
            System.out.println(info.getTitle() + ": " + info.getValue());
        }

        scanner.close();
    }

    static class Contact {
        private String name;
        private ContactInfo phone;
        private ContactInfo[] emails = new ContactInfo[3];
        private int emailCount = 0;
        private ContactInfo[] socials = new ContactInfo[5];
        private int socialCount = 0;

        public Contact(String name) {
            this.name = name;
        }

        public boolean rename(String newName) {
            if (newName == null || newName.isEmpty()) return false;
            this.name = newName;
            return true;
        }

        public ContactInfo addEmail(String localPart, String domain) {
            if (emailCount >= 3) return null;
            ContactInfo email = new Email(localPart + "@" + domain);
            emails[emailCount++] = email;
            return email;
        }

        public ContactInfo addEpamEmail(String firstName, String lastName) {
            if (emailCount >= 3) return null;
            ContactInfo email = new Email(firstName + "_" + lastName + "@epam.com") {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            emails[emailCount++] = email;
            return email;
        }

        public ContactInfo addPhoneNumber(int code, String number) {
            if (phone != null) return null;
            phone = new ContactInfo() {
                @Override
                public String getTitle() {
                    return "Tel";
                }

                @Override
                public String getValue() {
                    return "+" + code + " " + number;
                }
            };
            return phone;
        }

        public ContactInfo addTwitter(String twitterId) {
            return addSocialMedia("Twitter", twitterId);
        }

        public ContactInfo addInstagram(String instagramId) {
            return addSocialMedia("Instagram", instagramId);
        }

        public ContactInfo addSocialMedia(String title, String id) {
            if (socialCount >= 5) return null;
            ContactInfo social = new Social(title, id);
            socials[socialCount++] = social;
            return social;
        }

        public ContactInfo[] getInfo() {
            int size = 1 + (phone != null ? 1 : 0) + emailCount + socialCount;
            ContactInfo[] result = new ContactInfo[size];
            int index = 0;
            result[index++] = new NameContactInfo();
            if (phone != null) result[index++] = phone;
            for (int i = 0; i < emailCount; i++) result[index++] = emails[i];
            for (int i = 0; i < socialCount; i++) result[index++] = socials[i];
            return result;
        }

        // --- ContactInfo interface and nested classes ---
        public interface ContactInfo {
            String getTitle();
            String getValue();
        }

        private class NameContactInfo implements ContactInfo {
            @Override
            public String getTitle() {
                return "Name";
            }

            @Override
            public String getValue() {
                return name;
            }
        }

        public static class Email implements ContactInfo {
            private final String value;

            public Email(String value) {
                this.value = value;
            }

            @Override
            public String getTitle() {
                return "Email";
            }

            @Override
            public String getValue() {
                return value;
            }
        }

        public static class Social implements ContactInfo {
            private final String title;
            private final String id;

            public Social(String title, String id) {
                this.title = title;
                this.id = id;
            }

            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getValue() {
                return id;
            }
        }
    }
}
