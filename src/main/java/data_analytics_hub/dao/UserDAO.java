package data_analytics_hub.dao;

import data_analytics_hub.Session;
import data_analytics_hub.functions.TxtFileExecutor;
import data_analytics_hub.modal.User;

import java.util.ArrayList;
import java.util.Optional;

public class UserDAO extends SuperDAO<User> implements DAO<User>{

    public ArrayList<User> initData() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String[]> fileContent = Session.txtFileExecutor.readFileToArray(TxtFileExecutor.USERS);
        for(String[] ele : fileContent){
            users.add(new User(Integer.parseInt(ele[0]), ele[1], ele[2], ele[3], ele[4], ele[5]));
        }
        return users;
    }

    public void saveAll(){
        ArrayList<String[]> usersContent = new ArrayList<>();
        for (User user : Session.users) {
            usersContent.add(user.toStringArray());
        }
        Session.txtFileExecutor.rewriteFile(TxtFileExecutor.USERS, usersContent);
    }

    public boolean isUsernameExist(String username) {
        for (User user : Session.users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<User> get(int id) {
        for (User user : Session.users) {
            if (user.getUserId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<User> getAll() {
        return Session.users;
    }

    @Override
    public void save(User user) {
        Session.users.add(user);
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {
        Session.users.remove(user);
    }
}
