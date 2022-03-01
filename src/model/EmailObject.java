package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EmailObject implements Serializable {

    private Set<String> emailSet;

    public EmailObject() {
        this.emailSet = new HashSet<>();
    }

    public void add(String email) {
        this.emailSet.add(email);
    }

    public Set<String> getEmailSet() {
        return this.emailSet;
    }
}
