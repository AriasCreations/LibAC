package dev.zontreck.ariaslib.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonObject {
    private Map<String, Object> data;

    public JsonObject() {
        data = new HashMap<>();
    }

    public JsonObject(Map<String, Object> dat) {
        data = new HashMap<>(dat);
    }

    public static JsonObject parseJSON(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString.append(line);
        }
        return parseJsonObject(jsonString.toString());
    }

    private static JsonObject parseJsonObject(String jsonString) {
        JsonObject jsonObject = new JsonObject();
        jsonString = jsonString.trim();
        if (jsonString.startsWith("{") && jsonString.endsWith("}")) {
            jsonString = jsonString.substring(1, jsonString.length() - 1);
            String[] keyValuePairs = jsonString.split(",");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().replace("\"", "");
                    String value = keyValue[1].trim();
                    jsonObject.put(key, parseValue(value));
                }
            }
        }
        return jsonObject;
    }

    private static Object parseValue(String value) {
        if (value.startsWith("{") && value.endsWith("}")) {
            return parseJsonObject(value);
        } else if (value.startsWith("[") && value.endsWith("]")) {
            return parseJSONArray(value);
        } else if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        } else if (value.equalsIgnoreCase("true")) {
            return true;
        } else if (value.equalsIgnoreCase("false")) {
            return false;
        } else if (value.equalsIgnoreCase("null")) {
            return null;
        } else {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                try {
                    return Double.parseDouble(value);
                } catch (NumberFormatException ex) {
                    return value;
                }
            }
        }
    }

    private static List<Object> parseJSONArray(String jsonArray) {
        List<Object> list = new ArrayList<>();
        jsonArray = jsonArray.trim();
        if (jsonArray.startsWith("[") && jsonArray.endsWith("]")) {
            jsonArray = jsonArray.substring(1, jsonArray.length() - 1);
            String[] elements = jsonArray.split(",");
            for (String element : elements) {
                list.add(parseValue(element.trim()));
            }
        }
        return list;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public void merge(Map<String, Object> ret) {
        data.putAll(ret);
    }

    public Map<String, Object> getMap() {
        return new HashMap<>(data);
    }

    public void add(String key, Object value) {
        if (data.containsKey(key)) {
            Object existingValue = data.get(key);
            if (existingValue instanceof List) {
                ((List<Object>) existingValue).add(value);
            } else {
                List<Object> list = new ArrayList<>();
                list.add(existingValue);
                list.add(value);
                data.put(key, list);
            }
        } else {
            data.put(key, value);
        }
    }

    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (!first) {
                sb.append(",");
            }
            first = false;

            sb.append("\"");
            sb.append(escape(entry.getKey()));
            sb.append("\":");
            sb.append(toJSONValue(entry.getValue()));
        }

        sb.append("}");
        return sb.toString();
    }

    private String escape(String str) {
        if (str == null) return "";
        // Add necessary escape characters (e.g., double quotes, backslashes)
        // You can implement this method based on your specific requirements.
        // This is a simplified version for demonstration purposes.
        return str.replace("\"", "\\\"");
    }

    private String toJSONValue(Object value) {
        if (value instanceof String) {
            return "\"" + escape(value.toString()) + "\"";
        } else if (value instanceof JsonObject) {
            return ((JsonObject) value).toJSONString();
        } else if (value instanceof List) {
            return toJSONList((List<Object>) value);
        } else if (value instanceof Map<?, ?>) {
            return new JsonObject((Map<String, Object>) ((Map<?, ?>) value)).toJSONString();
        } else {
            return value.toString();
        }
    }

    private String toJSONList(List<Object> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        boolean first = true;
        for (Object item : list) {
            if (!first) {
                sb.append(",");
            }
            first = false;

            sb.append(toJSONValue(item));
        }

        sb.append("]");
        return sb.toString();
    }
}

