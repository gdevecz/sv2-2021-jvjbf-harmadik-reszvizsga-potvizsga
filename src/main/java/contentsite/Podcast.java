package contentsite;

import java.util.ArrayList;
import java.util.List;

public class Podcast implements Content {

    private String title;

    private List<String> speakers;

    private List<User> users = new ArrayList<>();

    public Podcast(String title, List<String> speakers) {
        this.title = title;
        this.speakers = speakers;
    }

    @Override
    public boolean isPremiumContent() {
        return false;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(users.stream().map(User::new).toList());
    }

    @Override
    public void click(User user) {
        if(!isPremiumContent()||user.isPremiumMember()) {
            users.add(user);
        }
    }

    public List<String> getSpeakers() {
        return speakers;
    }
}
