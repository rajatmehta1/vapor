package qas.qa.vo;

public class EntityVO {
   private String entityName;
   private String entityType;
   private String coveredTxt;
   private String prob;
   
    public EntityVO() {
	   
    }

	public String getEntityName() {
		return entityName;
	}
	
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getCoveredTxt() {
		return coveredTxt;
	}

	public void setCoveredTxt(String coveredTxt) {
		this.coveredTxt = coveredTxt;
	}

	public String getProb() {
		return prob;
	}

	public void setProb(String prob) {
		this.prob = prob;
	}
	   
	
   
}
