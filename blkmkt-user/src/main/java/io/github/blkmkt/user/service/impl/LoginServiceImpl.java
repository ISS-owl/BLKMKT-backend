package io.github.blkmkt.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.blkmkt.user.dao.UserDao;
import io.github.blkmkt.user.entity.UserEntity;
import io.github.blkmkt.user.exception.PhoneNumExistException;
import io.github.blkmkt.user.exception.UserExistException;
import io.github.blkmkt.user.service.LoginService;
import io.github.blkmkt.user.vo.RegisterVo;
import io.github.blkmkt.user.vo.UserLoginVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("loginService")
public class LoginServiceImpl extends ServiceImpl<UserDao, UserEntity> implements LoginService {
    @Override
    public UserEntity login(UserLoginVo loginVo) {
        String studentId = loginVo.getStudentId();
        UserEntity userEntity = this.getOne(new QueryWrapper<UserEntity>().eq("student_id", studentId));
        if (userEntity != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean matches = bCryptPasswordEncoder.matches(loginVo.getPassword(), userEntity.getPassword());
            if (matches) {
                // 隐藏用户密码
                userEntity.setPassword("");
                return userEntity;
            }
        }
        return null;
    }

    @Override
    public void register(RegisterVo registerVo) {
        // 查看信息是否唯一
        CheckStudentIdUnique(registerVo.getStudentId());
        CheckPhoneNumUnique(registerVo.getPhone());

        // 创建用户实体并设置用户信息
        UserEntity userEntity = new UserEntity();
        userEntity.setStudentId(registerVo.getStudentId());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(registerVo.getPassword());
        userEntity.setName(registerVo.getName());
        userEntity.setMobile(registerVo.getPhone());
        userEntity.setSex(registerVo.getSex());
        userEntity.setNickname(registerVo.getNickname());
        userEntity.setPassword(encodePassword);
        userEntity.setAddress(registerVo.getAddress());
        userEntity.setCreateTime(new Date());
        userEntity.setEnabled(1);

        this.save(userEntity);
    }

    private void CheckStudentIdUnique(String studentId) {
        Integer countId = this.baseMapper.selectCount(new QueryWrapper<UserEntity>().eq("student_id", studentId));
        if (countId > 0) {
            throw new UserExistException();
        }
    }

    private void CheckPhoneNumUnique(String phone) {
        Integer countMobile = this.baseMapper.selectCount(new QueryWrapper<UserEntity>().eq("mobile", phone));
        if (countMobile > 0) {
            throw new PhoneNumExistException();
        }
    }
}
