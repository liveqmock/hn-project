package hn.travel.persist.service.member;

import hn.travel.persist.entity.member.Member;
import hn.travel.persist.generic.dao.MemberSpecUtils;
import hn.travel.persist.repository.member.MemberDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

@Component
@Transactional
public class MemberService {
	
	private MemberDao memberDao;
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(Member member) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		member.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(member.getPassword().getBytes(), salt, HASH_INTERATIONS);
		member.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 查询所有会员信息
	 * 
	 * @return
	 */
	public List<Member> getAllMember(){
		return (List<Member>)memberDao.findAll();
	}
	
	/**
	 * 条件查询所有会员信息
	 * 
	 * @return
	 */
	public List<Member> getMembersByCriteria(Member member){
		return (List<Member>)memberDao.findAll(MemberSpecUtils.getMemberSpec(member));
	}
	
	/**
	 * 根据ID查询会员信息
	 * 
	 * @param id 会员ID
	 * @return
	 */
	public Member getMember(Long id){
		return memberDao.findOne(id);
	}
	
	/**
	 * 根据用户名查询会员信息
	 * 
	 * @param userName 用户名
	 * @return
	 */
	public Member findMemberByUserName(String userName){
		return memberDao.findByUserName(userName);
	}
	
	/**
	 * 根据手机号查询会员信息
	 * 
	 * @param mobile
	 * @return
	 */
	public Member findMemberByMobile(String mobile){
		return memberDao.findByMobile(mobile);
	}
	
	/**
	 * 根据邮箱地址查询会员信息
	 * 
	 * @param email
	 * @return
	 */
	public Member findMemberByEmail(String email){
		return memberDao.findByEmail(email);
	}
	
	/**
	 * 注册会员
	 * 
	 * @param member 会员信息
	 */
	public void registerMember(Member member){
		entryptPassword(member);
		memberDao.save(member);
	}
	
	/**
	 * 修改用户信息
	 * 
	 * @param member 用户信息
	 */
	public void updateMember(Member member){
		if (StringUtils.hasText(member.getPassword())) {
			entryptPassword(member);
		}
		memberDao.save(member);
	}
	
}
