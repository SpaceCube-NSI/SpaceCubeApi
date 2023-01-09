package fr.Mathis_Bruel.spacecube.spacecubeapi.api.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luzog
 * @version R-1.0.0
 * @date Mer. 9 June. 2021
 * @time In -3h
 * @apiNote To help to MC Plugins Configurations
 * <p>
 * {@docRoot} JSONLib - fr.luzog.json
 */
public class JSON {

    private Map<Object, Object> json;

    public JSON() {
        json = new HashMap<>();
    }

    public JSON(Map<Object, Object> base) {
        json = base;
    }

    public JSON(String base) {
        json = parseJSON(base).getJsonMap();
    }

    @SuppressWarnings("unchecked")
    private static String _vMap(HashMap<Object, Object> map) {
        String end = "";
        for (Object s : map.keySet()) {
            Object o = map.get(s);
            end += ",";
            if (s == null) {
                end += "null";
            } else if (s instanceof JSON) {
                end += ((JSON) s).build();
            } else if (s instanceof ArrayList) {
                end += _vList((ArrayList<Object>) s);
            } else if (s instanceof HashMap) {
                end += _vMap((HashMap<Object, Object>) s);
            } else if (s instanceof Integer) {
                end += s;
            } else if (s instanceof Long) {
                end += s + "l";
            } else if (s instanceof Double) {
                end += s;
            } else if (s instanceof Float) {
                end += s + "f";
            } else if (s instanceof Short) {
                end += s + "s";
            } else if (s instanceof Byte) {
                end += s + "b";
            } else if (s instanceof String) {
                end += "\"" + s + "\"";
            } else {
                end += "\"" + s + "\"";
            }
            end += ":";
            if (o == null) {
                end += "null";
            } else if (o instanceof JSON) {
                end += ((JSON) o).build();
            } else if (o instanceof ArrayList) {
                end += _vList((ArrayList<Object>) o);
            } else if (o instanceof Object[]) {
                end += _vTable((Object[]) o);
            } else if (o instanceof HashMap) {
                end += _vMap((HashMap<Object, Object>) o);
            } else if (o instanceof Integer || o instanceof Boolean) {
                end += o;
            } else if (o instanceof Long) {
                end += o + "l";
            } else if (o instanceof Double) {
                end += o;
            } else if (o instanceof Float) {
                end += o + "f";
            } else if (o instanceof Short) {
                end += o + "s";
            } else if (o instanceof Byte) {
                end += o + "b";
            } else if (o instanceof String) {
                end += "\"" + o + "\"";
            } else {
                end += "\"" + o + "\"";
            }
        }
        if (end.length() > 0 && end.charAt(0) == ',') {
            end = end.substring(1);
        }
        return "{" + end + "}";
    }

    private static String _vTable(Object[] table) {
        ArrayList<Object> l = new ArrayList<>();
        for (Object o : table)
            l.add(o);
        return _vList(l);
    }

    @SuppressWarnings("unchecked")
    private static String _vList(ArrayList<Object> list) {
        String end = "";
        for (Object o : list) {
            end += ",";
            if (o == null) {
                end += "null";
            } else if (o instanceof JSON) {
                end += ((JSON) o).build();
            } else if (o instanceof ArrayList) {
                end += _vList((ArrayList<Object>) o);
            } else if (o instanceof Object[]) {
                end += _vTable((Object[]) o);
            } else if (o instanceof HashMap) {
                end += _vMap((HashMap<Object, Object>) o);
            } else if (o instanceof Integer || o instanceof Boolean) {
                end += o;
            } else if (o instanceof Long) {
                end += o + "l";
            } else if (o instanceof Double) {
                end += o;
            } else if (o instanceof Float) {
                end += o + "f";
            } else if (o instanceof Short) {
                end += o + "s";
            } else if (o instanceof Byte) {
                end += o + "b";
            } else if (o instanceof String) {
                end += "\"" + o + "\"";
            } else {
                end += "\"" + o + "\"";
            }
        }
        if (end.length() > 0 && end.charAt(0) == ',') {
            end = end.substring(1);
        }
        return "[" + end + "]";
    }

    public static String build(Map<Object, Object> json) {
        return _vMap((HashMap<Object, Object>) json);
    }

    public static String build(String json) {
        return _vMap((HashMap<Object, Object>) parseJSON(json).getJsonMap());
    }

    /**
     * @throw <b>IllegalArgumentException</b> if the JSON syntax is not valid.
     */
    public static JSON parseJSON(String json) {
        JSON fin = new JSON();
        fin.setJsonMap(_fMap(json));
        return fin;
    }

    private static Map<Object, Object> _fMap(String map) {
        Map<Object, Object> finM = new HashMap<>();
        String lvl = "";
        List<String> fields = new ArrayList<>();
        String actualField = "";
        boolean isStr = false;
        for (int i = 0; i < map.length(); i++) {
            char c = map.charAt(i);
            if (c == '"' && (i == 0 || map.charAt(i - 1) != '\\')) isStr = !isStr;
            else if (!isStr && (c == '{' || c == '[')) lvl += c;
            else if (!isStr && c == '}') if (lvl.endsWith("{")) lvl = lvl.substring(0, lvl.length() - 1);
            else throw new IllegalArgumentException("Illegal JSON syntax. (error at '}' -> char." + i + ")");
            else if (!isStr && c == ']') if (lvl.endsWith("[")) lvl = lvl.substring(0, lvl.length() - 1);
            else throw new IllegalArgumentException("Illegal JSON syntax. (error at ']' -> char." + i + ")");

            if (lvl.length() == 1 && i == 0) actualField = "";
            else if (lvl.length() == 1 && i == map.length() - 2) fields.add(actualField + (c == ' ' ? "" : c));
            else if (lvl.length() == 1 && c == ',') {
                fields.add(actualField);
                actualField = "";
            } else if (!((c == ' ' || c == '\n') && !isStr)) actualField += c;
        }
        if (isStr) throw new IllegalArgumentException("Illegal JSON syntax. (error -> '\"' expected)");
        if (!lvl.equals(""))
            throw new IllegalArgumentException("Illegal JSON syntax. (error -> '" + (lvl.charAt(0) == '{' ? '}' : ']') + "' expected)");

        for (String field : fields) {
            if (!field.contains(":"))
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> ':' expected)");
            int spliterator = 0;
            for (int i = 0; i < field.length(); i++) {
                char c = field.charAt(i);
                if (c == '"' && (i == 0 || field.charAt(i - 1) != '\\')) isStr = !isStr;
                else if (!isStr && (c == '{' || c == '[')) lvl += c;
                else if (!isStr && c == '}') if (lvl.endsWith("{")) lvl = lvl.substring(0, lvl.length() - 1);
                else throw new IllegalArgumentException("Illegal JSON syntax. (error at '}' -> char." + i + ")");
                else if (!isStr && c == ']') if (lvl.endsWith("[")) lvl = lvl.substring(0, lvl.length() - 1);
                else throw new IllegalArgumentException("Illegal JSON syntax. (error at ']' -> char." + i + ")");

                if (!isStr && lvl.length() == 0 && c == ':') if (spliterator == 0) spliterator = i;
                else throw new IllegalArgumentException("Illegal JSON syntax. (error at ':' -> char." + i + ")");

            }
            if (spliterator == 0) throw new IllegalArgumentException("Illegal JSON syntax. (error -> ':' expected)");
            String k = field.substring(0, spliterator);
            String v = field.substring(spliterator + 1);
            Object finK = null;
            Object finV = null;
            if (k.startsWith("\"") && k.endsWith("\"")) finK = k.substring(1, k.length() - 1);
            else if (k.startsWith("{") && k.endsWith("}")) finK = _fMap(k);
            else if (k.startsWith("[") && k.endsWith("]")) finK = _fList(k);
            else if (k.equalsIgnoreCase("true")) finK = true;
            else if (k.equalsIgnoreCase("false")) finK = false;
            else if (k.equalsIgnoreCase("null")) finK = null;
            else if (k.toLowerCase().endsWith("l")) try {
                finK = Long.parseLong(k.replace("l", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + k + "')");
            }
            else if (k.toLowerCase().endsWith("f")) try {
                finK = Float.parseFloat(k.replace("f", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + k + "')");
            }
            else if (k.toLowerCase().endsWith("s")) try {
                finK = Short.parseShort(k.replace("s", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + k + "')");
            }
            else if (k.toLowerCase().endsWith("b")) try {
                finK = Byte.parseByte(k.replace("b", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + k + "')");
            }
            else try {
                    finK = Integer.parseInt(k);
                } catch (NumberFormatException e) {
                    try {
                        finK = Double.parseDouble(k);
                    } catch (NumberFormatException ee) {
                        throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + k + "')");
                    }
                }
            if (v.startsWith("\"") && v.endsWith("\"")) finV = v.substring(1, v.length() - 1);
            else if (v.startsWith("{") && v.endsWith("}")) finV = _fMap(v);
            else if (v.startsWith("[") && v.endsWith("]")) finV = _fList(v);
            else if (v.equalsIgnoreCase("true")) finV = true;
            else if (v.equalsIgnoreCase("false")) finV = false;
            else if (v.equalsIgnoreCase("null")) finV = null;
            else if (v.toLowerCase().endsWith("l")) try {
                finV = Long.parseLong(v.replace("l", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + v + "')");
            }
            else if (v.toLowerCase().endsWith("f")) try {
                finV = Float.parseFloat(v.replace("f", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + v + "')");
            }
            else if (v.toLowerCase().endsWith("s")) try {
                finV = Short.parseShort(v.replace("s", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + v + "')");
            }
            else if (v.toLowerCase().endsWith("b")) try {
                finV = Byte.parseByte(v.replace("b", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + v + "')");
            }
            else try {
                    finV = Integer.parseInt(v);
                } catch (NumberFormatException e) {
                    try {
                        finV = Double.parseDouble(v);
                    } catch (NumberFormatException ee) {
                        throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + v + "')");
                    }
                }
            finM.put(finK, finV);
        }
        return finM;
    }

    private static List<Object> _fList(String list) {
        List<Object> finL = new ArrayList<>();
        String lvl = "";
        List<String> fields = new ArrayList<>();
        String actualField = "";
        boolean isStr = false;
        for (int i = 0; i < list.length(); i++) {
            char c = list.charAt(i);
            if (c == '"' && (i == 0 || list.charAt(i - 1) != '\\')) isStr = !isStr;
            else if (!isStr && (c == '{' || c == '[')) lvl += c;
            else if (!isStr && c == '}') if (lvl.endsWith("{")) lvl = lvl.substring(0, lvl.length() - 1);
            else throw new IllegalArgumentException("Illegal JSON syntax. (error at '}' -> char." + i + ")");
            else if (!isStr && c == ']') if (lvl.endsWith("[")) lvl = lvl.substring(0, lvl.length() - 1);
            else throw new IllegalArgumentException("Illegal JSON syntax. (error at ']' -> char." + i + ")");

            if (lvl.length() == 1 && i == 0) actualField = "";
            else if (lvl.length() == 1 && i == list.length() - 2) fields.add(actualField + c);
            else if (lvl.length() == 1 && c == ',') {
                fields.add(actualField);
                actualField = "";
            } else if (!((c == ' ' || c == '\n') && !isStr)) actualField += c;
        }
        if (isStr) throw new IllegalArgumentException("Illegal JSON syntax. (error -> '\"' expected)");
        if (!lvl.equals(""))
            throw new IllegalArgumentException("Illegal JSON syntax. (error -> '" + (lvl.charAt(0) == '{' ? '}' : ']') + "' expected)");

        for (String field : fields) {
            Object fin = null;
            if (field.startsWith("\"") && field.endsWith("\"")) fin = field.substring(1, field.length() - 1);
            else if (field.startsWith("{") && field.endsWith("}")) fin = _fMap(field);
            else if (field.startsWith("[") && field.endsWith("]")) fin = _fList(field);
            else if (field.equalsIgnoreCase("true")) fin = true;
            else if (field.equalsIgnoreCase("false")) fin = false;
            else if (field.equalsIgnoreCase("null")) fin = null;
            else if (field.toLowerCase().endsWith("l")) try {
                fin = Long.parseLong(field.replace("l", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + field + "')");
            }
            else if (field.toLowerCase().endsWith("f")) try {
                fin = Float.parseFloat(field.replace("f", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + field + "')");
            }
            else if (field.toLowerCase().endsWith("s")) try {
                fin = Short.parseShort(field.replace("s", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + field + "')");
            }
            else if (field.toLowerCase().endsWith("b")) try {
                fin = Byte.parseByte(field.replace("b", ""));
            } catch (NumberFormatException ee) {
                throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + field + "')");
            }
            else try {
                    fin = Integer.parseInt(field);
                } catch (NumberFormatException e) {
                    try {
                        fin = Double.parseDouble(field);
                    } catch (NumberFormatException ee) {
                        throw new IllegalArgumentException("Illegal JSON syntax. (error -> unknow '" + field + "')");
                    }
                }
            finL.add(fin);
        }
        return finL;
    }

    /**
     * @return Formatted JSON with 2 tab spaces.
     */
    public static String buildFormatOf(String json) {
        return buildFormatOf(json, 2);
    }

    public static String buildFormatOf(String json, int tabSpaces) {
        String build = build(json);
        String fin = "";
        boolean isStr = false;
        int tab = 0;

        for (int i = 0; i < build.length(); i++) {
            char c = build.charAt(i);

            if (c == '"') {
                isStr = !isStr;
                fin += c;
            } else if (!isStr && (c == '{' || c == '[')) {
                try {
                    fin += build.charAt(i - 1) == ',' || build.charAt(i - 1) == '[' || build.charAt(i - 1) == '{' ? "" : "\n" + _tab(tab, tabSpaces);
                } catch (Exception e) {
                }
                try {
                    if ((c == '{' && build.charAt(i + 1) == '}') || (c == '[' && build.charAt(i + 1) == ']')) {
                        fin += c + "" + build.charAt(i + 1);
                        i++;
                    } else {
                        fin += c + "\n" + _tab(tab + 1, tabSpaces);
                        tab += 1;
                    }
                } catch (Exception e) {
                    fin += c + "\n" + _tab(tab + 1, tabSpaces);
                    tab += 1;
                }
            } else if (!isStr && (c == '}' || c == ']')) {
                tab -= 1;
                fin += "\n" + _tab(tab, tabSpaces) + c;
            } else if (!isStr && c == ',') {
                fin += c + "\n" + _tab(tab, tabSpaces);
            } else if (!isStr && c == ':') {
                fin += c + " ";
            } else fin += c;

        }

        return fin.startsWith("\n") ? fin.replaceFirst("\n", "") : fin;
    }

    private static String _tab(int tab, int tabSpaces) {
        String t = "";
        for (int i = 0; i < tab * tabSpaces; i++)
            t += " ";
        return t;
    }

    public JSON add(Object k, Object v) {
        json.put(k, v);
        return this;
    }

    public JSON add(Object k, Object... list) {
        List<Object> l = new ArrayList<>();
        for (Object o : list)
            l.add(o);
        json.put(k, l);
        return this;
    }

    public JSON set(Object k, Object v) {
        json.replace(k, v);
        return this;
    }

    public JSON set(Object k, Object... list) {
        List<Object> l = new ArrayList<>();
        for (Object o : list)
            l.add(o);
        json.replace(k, l);
        return this;
    }

    public int length() {
        return json.size();
    }

    public Map<Object, Object> getJsonMap() {
        return json;
    }

    public JSON setJsonMap(Map<Object, Object> jsonMap) {
        json = jsonMap;
        return this;
    }

    public boolean containsKey(Object key) {
        return json.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return json.containsValue(value);
    }

    public Object get(Object key) {
        return json.get(key);
    }

    public String getStr(Object key) {
        return json.get(key).toString();
    }

    public int getInt(Object key) {
        return Integer.parseInt(json.get(key).toString());
    }

    public long getLong(Object key) {
        return Long.parseLong(json.get(key).toString());
    }

    public double getDouble(Object key) {
        return Double.parseDouble(json.get(key).toString());
    }

    public float getFloat(Object key) {
        return Float.parseFloat(json.get(key).toString());
    }

    public short getShort(Object key) {
        return Short.parseShort(json.get(key).toString());
    }

    public byte getByte(Object key) {
        return Byte.parseByte(json.get(key).toString());
    }

    public boolean getBoolean(Object key) {
        return Boolean.parseBoolean(json.get(key).toString());
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Object> getList(Object key) {
        return (ArrayList<Object>) json.get(key);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<String> getStrList(Object key) {
        return (ArrayList<String>) json.get(key);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getIntList(Object key) {
        return (ArrayList<Integer>) json.get(key);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<HashMap<Object, Object>> getMapList(Object key) {
        return (ArrayList<HashMap<Object, Object>>) json.get(key);
    }

    @SuppressWarnings("unchecked")
    public HashMap<Object, Object> getMap(Object key) {
        return (HashMap<Object, Object>) json.get(key);
    }

    @SuppressWarnings("unchecked")
    public HashMap<Object, String> getStrMap(Object key) {
        return (HashMap<Object, String>) json.get(key);
    }

    @SuppressWarnings("unchecked")
    public HashMap<Object, Integer> getIntMap(Object key) {
        return (HashMap<Object, Integer>) json.get(key);
    }

    @SuppressWarnings("unchecked")
    public HashMap<Object, ArrayList<Object>> getListMap(Object key) {
        return (HashMap<Object, ArrayList<Object>>) json.get(key);
    }

    public String build() {
        return _vMap((HashMap<Object, Object>) json);
    }

    /**
     * @return Formatted JSON with 2 tab spaces.
     */
    public String buildFormat() {
        return buildFormat(2);
    }

    public String buildFormat(int tabSpaces) {
        return buildFormatOf(build(), tabSpaces);
    }

    public class Array {

        private List<Object> array;

        public Array() {
            setArray(new ArrayList<>());
        }

        @SuppressWarnings("unchecked")
        public String build() {
            return _vList((ArrayList<Object>) json);
        }

        public List<Object> getArray() {
            return array;
        }

        public void setArray(List<Object> array) {
            this.array = array;
        }

        public Array add(Object o) {
            array.add(o);
            return this;
        }

        public Array add(Object... o) {
            for (Object obj : o)
                array.add(obj);
            return this;
        }

        public boolean contains(Object o) {
            return array.contains(o);
        }

        public Object get(int index) {
            return array.get(index);
        }

        public String getStr(int index) {
            return array.get(index).toString();
        }

        public int getInt(int index) {
            return Integer.parseInt(array.get(index).toString());
        }

        public long getLong(int index) {
            return Long.parseLong(array.get(index).toString());
        }

        public double getDouble(int index) {
            return Double.parseDouble(array.get(index).toString());
        }

        public float getFloat(int index) {
            return Float.parseFloat(array.get(index).toString());
        }

        public short getShort(int index) {
            return Short.parseShort(array.get(index).toString());
        }

        public byte getByte(int index) {
            return Byte.parseByte(array.get(index).toString());
        }

        public boolean getBoolean(int index) {
            return Boolean.parseBoolean(array.get(index).toString());
        }

        @SuppressWarnings("unchecked")
        public ArrayList<Object> getList(int index) {
            return (ArrayList<Object>) array.get(index);
        }

        @SuppressWarnings("unchecked")
        public ArrayList<String> getStrList(int index) {
            return (ArrayList<String>) array.get(index);
        }

        @SuppressWarnings("unchecked")
        public ArrayList<Integer> getIntList(int index) {
            return (ArrayList<Integer>) array.get(index);
        }

        @SuppressWarnings("unchecked")
        public ArrayList<HashMap<Object, Object>> getMapList(int index) {
            return (ArrayList<HashMap<Object, Object>>) array.get(index);
        }

        @SuppressWarnings("unchecked")
        public HashMap<Object, Object> getMap(int index) {
            return (HashMap<Object, Object>) array.get(index);
        }

        @SuppressWarnings("unchecked")
        public HashMap<Object, String> getStrMap(int index) {
            return (HashMap<Object, String>) array.get(index);
        }

        @SuppressWarnings("unchecked")
        public HashMap<Object, Integer> getIntMap(int index) {
            return (HashMap<Object, Integer>) array.get(index);
        }

        @SuppressWarnings("unchecked")
        public HashMap<Object, ArrayList<Object>> getListMap(int index) {
            return (HashMap<Object, ArrayList<Object>>) array.get(index);
        }
    }

}


