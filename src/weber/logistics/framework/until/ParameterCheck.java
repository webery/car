package weber.logistics.framework.until;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterCheck {

	public static boolean checkObjectId(String objectId) {

		if (objectId == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[a-zA-Z_0-9]{24}");
		Matcher matcher = pattern.matcher(objectId);
		if (matcher.matches()) {
			return true;
		}

		return false;
	}

	public static boolean checkCityId(Integer cityId) {
		if (cityId == null) {
			return false;
		}
		if (cityId > 0) {
			return true;
		}
		return false;
	}

	public static boolean checkProvinceId(Integer provinceId) {
		if (provinceId == null) {
			return false;
		}
		if (provinceId > 10 || provinceId < 83) {
			return true;
		}

		return false;
	}

	public static boolean checkNationId(Integer nationId) {

		if (nationId == null) {
			return false;
		}
		if (nationId > 100 || nationId < 157) {
			return true;
		}

		return false;
	}

	public static boolean checkEmpId(String empId) {

		if (empId == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[A-Za-z0-9]{5,10}");
		Matcher matcher = pattern.matcher(empId);
		if (matcher.matches()) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {

		System.out.println(ParameterCheck
				.checkObjectId("5513f517b1420296011a2dc1"));
	}
}
