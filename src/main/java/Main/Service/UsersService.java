package Main.Service;

import Main.DAO.UserDAO;
import Main.Models.UserAuth;
import Main.Models.UsersEntity;
import Main.Utils.HashingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UserDAO dao;

    public Boolean Signup(UsersEntity body) {
        UsersEntity u = body;
        /**
         * Never store your passwords in plain, Use Hash Algorithms :)
         * SHA256 is used here
         * */
        u.setPassword(HashingUtils.doHash(u.getPassword()));
        return dao.save(u);
    }

    public List<UsersEntity> GetAll() {
        return dao.findAll();
    }

    public Boolean Signin(UserAuth body) {
        /**
         * Use Hash Algorithms before querying to the DB too !
         * */
        return dao.findAllBy2Properties("id", body.getId(), "Password", HashingUtils.doHash(body.getPassword())).size() > 0;
    }

    public UsersEntity Get(int id) {
        return dao.findAllBy1Properties("id", id).get(0);
    }
}
