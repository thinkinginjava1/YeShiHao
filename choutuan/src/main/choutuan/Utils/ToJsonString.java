package Utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import domain.HotelHome;
import domain.User;

public class ToJsonString {
    private static XStream xStream =null;
    static {
      xStream = new XStream(new JettisonMappedXmlDriver());
    }
    public static String toUserJsonString(User user, String ElementName) {
        xStream.alias(ElementName, user.getClass());
        xStream.setMode(XStream.NO_REFERENCES);
        String JsonString = xStream.toXML(user);
        return JsonString;
    }
    public static String toHotelHomeJsonString(HotelHome hotelHome, String ElementName) {
        xStream.alias(ElementName, hotelHome.getClass());
        xStream.setMode(XStream.NO_REFERENCES);
        String JsonString = xStream.toXML(hotelHome);
        return JsonString;
    }
    public static  String toJsonString(Object object,String ElementName){
        xStream.alias(ElementName, object.getClass());
        xStream.setMode(XStream.NO_REFERENCES);
        String JsonString = xStream.toXML(object);
        return JsonString;
    }
}
