package contentsite;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ContentService {

    private Set<User> allUsers = new HashSet<>();

    private Set<Content> allContent = new HashSet<>();

    void registerUser(String name, String password) {
        if (allUsers.stream().anyMatch(u -> u.getUserName().equals(name))) {
            throw new IllegalArgumentException("Username is already taken: " + name);
        }
        allUsers.add(new User(name, password));
    }

    public void addContent(Content content) {
        if (allContent.stream().anyMatch(c -> c.getTitle().equals(content.getTitle()))) {
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }
        allContent.add(content);
    }

    public Set<User> getAllUsers() {
        return allUsers;
    }

    public Set<Content> getAllContent() {
        return allContent;
    }

    public void logIn(String name, String password) {
        User actualUser = getUserFromName(name);
        if (actualUser.getPassword() != (name + password).hashCode()) {
            throw new IllegalArgumentException("Password is Invalid!");
        }
        actualUser.setLogIn(true);
    }

    public void clickOnContent(User user, Content content) {
        if (!user.isLogIn()) {
            throw new IllegalStateException("Log in to watch this content!");
        }
        if (content.isPremiumContent() && !user.isPremiumMember()) {
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        }
        content.click(user);
    }

    private User getUserFromName(String name) {
        User actualUser = null;
        Iterator<User> i = allUsers.iterator();
        while (i.hasNext()) {
            User temp = i.next();
            if (temp.getUserName().equals(name)) {
                actualUser = temp;
            }
        }
        if (actualUser == null) {
            throw new IllegalArgumentException("Username is wrong!");
        }
        return actualUser;
    }
}
