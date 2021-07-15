package net.guizhanss.minecraft.mobcapturer.utils;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

import javax.annotation.Nonnull;

public class HumanizeUtil {
    @Nonnull
    public static String getBoolean(boolean bool){
        return bool ? "是" : "否";
    }

    @Nonnull
    public static String getCatType(@Nonnull String type){
        switch(type.toLowerCase()){
            case "tabby":
                return "虎斑猫";
            case "tuxedo":
                return "西服猫";
            case "red":
                return "红虎斑猫";
            case "siamese":
                return "暹罗猫";
            case "british_shorthair":
                return "英国短毛猫";
            case "calico":
                return "花猫";
            case "persian":
                return "波斯猫";
            case "ragdoll":
                return "布偶猫";
            case "white":
                return "白猫";
            case "jellie":
                return "Jellie";
            case "black":
                return "黑猫";
            default:
                return ChatUtils.humanize(type);
        }
    }

    @Nonnull
    public static String getChatColor(@Nonnull String color){
        switch(color.toLowerCase()){
            case "black":
                return "黑色";
            case "dark_blue":
                return "深蓝色";
            case "dark_green":
                return "深绿色";
            case "dark_aqua":
                return "湖蓝色";
            case "dark_red":
                return "深红色";
            case "dark_purple":
                return "紫色";
            case "gold":
                return "金色";
            case "gray":
                return "灰色";
            case "dark_gray":
                return "深灰色";
            case "blue":
                return "蓝色";
            case "green":
                return "绿色";
            case "aqua":
                return "天蓝色";
            case "red":
                return "红色";
            case "light_purple":
                return "粉红色";
            case "yellow":
                return "黄色";
            case "white":
                return "白色";
            default:
                return ChatUtils.humanize(color);
        }
    }

    @Nonnull
    public static String getHorseStyle(@Nonnull String style){
        switch(style.toLowerCase()){
            case "none":
                return "无";
            case "white":
                return "白色";
            case "whitefield":
                return "白色条纹";
            case "white_dots":
                return "白色斑点";
            case "black_dots":
                return "黑色斑点";
            default:
                return ChatUtils.humanize(style);
        }
    }

    @Nonnull
    public static String getHorseColor(@Nonnull String color){
        switch(color.toLowerCase()){
            case "white":
                return "白色";
            case "creamy":
                return "奶油色";
            case "chestnut":
                return "栗色";
            case "brown":
                return "褐色";
            case "black":
                return "黑色";
            case "gray":
                return "灰色";
            case "dark_brown":
                return "深褐色";
            default:
                return ChatUtils.humanize(color);
        }
    }

    @Nonnull
    public static String getRabbitType(@Nonnull String type){
        switch(type.toLowerCase()){
            case "brown":
                return "褐色";
            case "white":
                return "白色";
            case "black":
                return "黑色";
            case "black_and_white":
                return "黑白相间";
            case "gold":
                return "金色";
            case "salt_and_pepper":
                return "胡椒盐色";
            case "the_killer_bunny":
                return "杀手兔";
            default:
                return ChatUtils.humanize(type);
        }
    }

    @Nonnull
    public static String getSheepColor(@Nonnull String color){
        switch(color.toLowerCase()){
            case "white":
                return "白色";
            case "orange":
                return "橙色";
            case "magenta":
                return "品红色";
            case "light_blue":
                return "淡蓝色";
            case "yellow":
                return "黄色";
            case "lime":
                return "黄绿色";
            case "pink":
                return "粉红色";
            case "gray":
                return "灰色";
            case "light_gray":
                return "淡灰色";
            case "cyan":
                return "青色";
            case "purple":
                return "紫色";
            case "blue":
                return "蓝色";
            case "brown":
                return "棕色";
            case "green":
                return "绿色";
            case "red":
                return "红色";
            case "black":
                return "黑色";
            default:
                return ChatUtils.humanize(color);
        }
    }

    @Nonnull
    public static String getPandaGene(@Nonnull String gene){
        switch(gene.toLowerCase()){
            case "normal":
                return "普通";
            case "aggressive":
                return "好斗";
            case "lazy":
                return "懒惰";
            case "worried":
                return "发愁";
            case "playful":
                return "顽皮";
            case "weak":
                return "虚弱";
            case "brown":
                return "棕色";
            default:
                return ChatUtils.humanize(gene);
        }
    }

    @Nonnull
    public static String getPuffState(int level){
        switch(level){
            case 0:
                return "未膨胀";
            case 1:
                return "半膨胀";
            case 2:
                return "完全膨胀";
            default:
                return "未知";
        }
    }

    @Nonnull
    public static String getTropicalFishPattern(@Nonnull String pattern){
        switch(pattern.toLowerCase()){
            case "flopper":
                return "飞翼类";
            case "glitter":
                return "闪鳞类";
            case "betty":
                return "背蒂类";
            case "stripey":
                return "条纹类";
            case "blockfish":
                return "方身类";
            case "clayfish":
                return "陶鱼类";
            case "kob":
                return "石首类";
            case "snooper":
                return "窥伺类";
            case "brinely":
                return "咸水类";
            case "sunstreak":
                return "日纹类";
            case "dasher":
                return "速跃类";
            case "spotty":
                return "多斑类";
            default:
                return ChatUtils.humanize(pattern);
        }
    }

    @Nonnull
    public static String getVillagerProfession(@Nonnull String profession){
        switch(profession.toLowerCase()){
            case "nitwit":
                return "傻子";
            case "armorer":
                return "盔甲匠";
            case "butcher":
                return "屠夫";
            case "cartographer":
                return "制图师";
            case "cleric":
                return "牧师";
            case "farmer":
                return "农民";
            case "fisherman":
                return "渔夫";
            case "fletcher":
                return "制箭师";
            case "leatherworker":
                return "皮匠";
            case "librarian":
                return "图书管理员";
            case "mason":
                return "石匠";
            case "shepherd":
                return "牧羊人";
            case "toolsmith":
                return "工具匠";
            case "weaponsmith":
                return "武器匠";
            default:
                return ChatUtils.humanize(profession);
        }
    }
}
