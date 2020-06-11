package co.bywarp.lightkit.test.json;

import co.bywarp.lightkit.json.JsonKey;

import java.util.ArrayList;
import java.util.List;

public class ReflectionJsonObject {

    public static class ReflectionJsonObject2 {

        @JsonKey(name="name")
        private String name = "Bob";

        @JsonKey(name="age")
        private int age = 37;

        public ReflectionJsonObject2() {
        }

        public ReflectionJsonObject2(String name,
                                     int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "ReflectionJsonObject2{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    @JsonKey(name="number")
    private int number;

    @JsonKey(name="enabled")
    private boolean enabled;

    @JsonKey(name="embed", type = ReflectionJsonObject2.class)
    private ReflectionJsonObject2 embed;

    @JsonKey(name="list", type = ReflectionJsonObject2.class)
    private List<ReflectionJsonObject2> embedList;

    @JsonKey(name="strings", type = String.class)
    private List<String> stringList;

    public ReflectionJsonObject() {
    }

    public ReflectionJsonObject(int number,
                                boolean enabled,
                                ReflectionJsonObject2 embed,
                                List<ReflectionJsonObject2> embedList,
                                List<String> stringList) {
        this.number = number;
        this.enabled = enabled;
        this.embed = embed;
        this.embedList = embedList;
        this.stringList = stringList;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public ReflectionJsonObject2 getEmbed() {
        return embed;
    }


    @Override
    public String toString() {
        return "ReflectionJsonObject{" +
                "number=" + number +
                ", enabled=" + enabled +
                ", embed=" + embed +
                ", embedList=" + embedList +
                ", stringList=" + stringList +
                '}';
    }
}
