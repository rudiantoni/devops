
public final class Util {
  private Util(){}

  public static DataModelC toDataModelC(String name, String country) {
    return new DataModelC(name, country);
  }

  public static DataModelC toDataModelC(DataModelB dmB) {
    return new DataModelC(dmB.getName(), dmB.getCountry());
  }

}
