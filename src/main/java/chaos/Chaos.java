package chaos;

import java.util.*;

public class Chaos implements InterfaceFa{
    private String name;
    private Integer age;
    private Integer sex;
    public static void main(String[] args) {
        Singleton singleton = Singleton.INSTANCE;
        singleton.whateverMethod();
        String a = "AAA";
        String b = "aaa";
        float f = 3.4f;
        short s1 = 1;
        s1 = (short) (s1 + 1);
        s1 += 1;
        System.out.println(a.compareToIgnoreCase(b));

        List<String> strings = new ArrayList<>();
    }

    public static int removeDuplicates(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(10);
        for (int i = 0; i < nums.length; i++) {
            //value不为0
            if(map.get(nums[i]) != null){
                Integer count = map.get(nums[i]);
                map.put(nums[i], count++);
            }else {
                map.put(nums[i],1);
            }
        }
        Set<Map.Entry<Integer,Integer>> set = map.entrySet();
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chaos chaos = (Chaos) o;
        return Objects.equals(name, chaos.name) &&
                Objects.equals(age, chaos.age) &&
                Objects.equals(sex, chaos.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex);
    }
}
