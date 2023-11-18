
public class DataModelC {
  private String newName;
  private String newCountry;

  public DataModelC(String newName, String newCountry) {
    this.newName = newName;
    this.newCountry = newCountry;
  }

  public String getNewName() {
    return newName;
  }

  public void setNewName(String newName) {
    this.newName = newName;
  }

  public String getNewCountry() {
    return newCountry;
  }

  public void setNewCountry(String newCountry) {
    this.newCountry = newCountry;
  }

  @Override
  public String toString() {
    return "DataModelC{" +
      "newName='" + newName + '\'' +
      ", newCountry='" + newCountry + '\'' +
      '}';
  }

}
