package bd.ohedul.erp.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bd.ohedul.erp.dto.BuyerDto;
import bd.ohedul.erp.dto.User;
import bd.ohedul.erp.model.Authority;
import bd.ohedul.erp.model.Buyer;
import bd.ohedul.erp.repository.BuyerRepository;

@Service
public class UserService {

	private final BuyerRepository repository;

	@Autowired
	public UserService(BuyerRepository repository) {
		this.repository = repository;
	}

	public Buyer saveUser(BuyerDto user) {
		try {
			Buyer existingByemail = repository.findByEmail(user.getEmail());
			if (existingByemail != null) {
				return null;
			}
			Buyer buyer = new Buyer();
			String passEncrypted = getEncryptedPassWord(user.getPassword());
			buyer.setFirstName(user.getFirstName());
			buyer.setLastName(user.getLastName());
			buyer.setFullName(user.getFirstName() + " " + user.getLastName());
			buyer.setEmail(user.getEmail());
			buyer.setPassword(passEncrypted);
			buyer.setAuthority(Authority.CLIENT);
			repository.save(buyer);
			if (buyer.getUserId() != null) {
				return buyer;
			}
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	public Buyer getUser(User user) {
		try {
			Buyer existingByemail = repository.findByEmail(user.getEmail());
			if (existingByemail != null) {
				String password = user.getPassword();
				String encrypted = getEncryptedPassWord(password);
				if (existingByemail.getPassword().equals(encrypted)) {
					return existingByemail;
				}
				return null;
			}
			return existingByemail;
		} catch (Exception e) {
			return null;
		}
	}

	private String getEncryptedPassWord(String plain) {
		String b64encoded = Base64.getEncoder().encodeToString(plain.getBytes());

		String reverse = new StringBuffer(b64encoded).reverse().toString();

		StringBuilder tmp = new StringBuilder();
		final int OFFSET = 4;
		for (int i = 0; i < reverse.length(); i++) {
			tmp.append((char) (reverse.charAt(i) + OFFSET));
		}
		return tmp.toString();
	}

	public String getDecryptedPassword(String secret) {
		StringBuilder tmp = new StringBuilder();
		final int OFFSET = 4;
		for (int i = 0; i < secret.length(); i++) {
			tmp.append((char) (secret.charAt(i) - OFFSET));
		}

		String reversed = new StringBuffer(tmp.toString()).reverse().toString();
		return new String(Base64.getDecoder().decode(reversed));
	}

}
