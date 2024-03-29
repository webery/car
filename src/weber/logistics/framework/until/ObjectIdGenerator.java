/*
 * Copyright (c) 2008-2014 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package weber.logistics.framework.until;

/**
 * Creates new {@code ObjectId} instances as IDs for MongoDB Documents.
 *
 * @since 3.0
 */
public class ObjectIdGenerator implements IdGenerator {
	@Override
	public Object generate() {
		return new ObjectId();
	}

	public static synchronized String generateString() {
		return new ObjectId().toString();
	}

	public static boolean check(String str) {
		if (str == null) {
			return false;
		}
		if (str.matches("[A-Za-z0-9]{6}")) {
			return true;
		}
		return false;

	}

	public static void main(String[] args) {
		ObjectIdGenerator test = new ObjectIdGenerator();
		for (int i = 0; i < 20; i++) {
			System.out.println(test.generate().toString());
		}
		// System.out.println(ObjectIdGenerator.check("1F1111"));
	}
}
