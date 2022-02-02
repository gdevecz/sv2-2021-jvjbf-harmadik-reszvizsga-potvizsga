package contentsite;

import java.util.ArrayList;
import java.util.List;

public class Video implements Content {

    private String title;

    private int length;

    private List<User> users = new ArrayList<>();

    public Video(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public void click(User user) {
        users.add(user);
    }

    @Override
    public boolean isPremiumContent() {
        return length > 15;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(users.stream().map(User::new).toList());
    }

    public int getLength() {
        return length;
    }
}
