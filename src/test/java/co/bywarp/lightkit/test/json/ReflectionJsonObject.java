package co.bywarp.lightkit.test.json;

import co.bywarp.lightkit.json.JsonKey;

public class ReflectionJsonObject {

    public class ReflectionJsonObject2 {

        @JsonKey(name="name")
        private String name = "Bob";

        @JsonKey(name="age")
        private int age = 37;

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
    private int number = 10;

    @JsonKey(name="enabled")
    private boolean enabled = true;

    @JsonKey(name="embed", embed = true)
    private ReflectionJsonObject2 embed = new ReflectionJsonObject2();

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
                '}';
    }
}
