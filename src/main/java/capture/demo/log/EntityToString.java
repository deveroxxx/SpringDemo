package capture.demo.log;

import java.lang.reflect.Field;

import capture.demo.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityToString {

	public static String log(Object obj) {
		try {
			if (obj instanceof BaseEntity) {
				StringBuilder result = new StringBuilder();
				result.append(obj.getClass().getSimpleName()).append("[id=").append(((BaseEntity) obj).getId());


				for (Field field : obj.getClass().getDeclaredFields()) {
					if (field.getAnnotation(ExcludeFromLog.class) == null) {
						field.setAccessible(true);
						Object value = field.get(obj);
						result.append(", ");
						result.append(toString(field.getName(), value));
					}
				}
				result.append("]");
				return result.toString();
			} else {
				throw new RuntimeException("Must be used on entities whose extends " + BaseEntity.class.getCanonicalName());
			}
		} catch (Exception e) {
			log.error("Error during logging: " + e);
			return "Error during logging";
		}
	}

	private static String toString(String name, Object o) {
		if (o == null) return name + "=null";
		if (o instanceof BaseEntity) {
			return name + "[id=" + ((BaseEntity) o).getId() + "]";
		} else {
			return name + "=" + o.toString();
		}
	}

}
