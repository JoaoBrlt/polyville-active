package fr.unice.ps7.al1.shops.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.ps7.al1.common.model.Publication;
import org.pf4j.Extension;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Discount.
 * <p>
 * Represents a general purpose discount.
 */
@Entity
@Extension
public class Discount implements Publication {
	/**
	 * The discount identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="discount_sequence")
	@SequenceGenerator(name = "discount_sequence", sequenceName = "discount_sequence", allocationSize=1)
	private Long id;

	/**
	 * The discount title.
	 */
	private String title;

	/**
	 * The discount value.
	 */
	private Double value;

	/**
	 * Whether the discount is a percentage.
	 */
	private boolean isPercentage;

	/**
	 * The discount condition.
	 */
	private String condition;

	/**
	 * The associated shop.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name="shop_id", referencedColumnName = "id", nullable = false)
	private Shop shop;

	/**
	 * The creation date.
	 */
	private final LocalDateTime createdAt;

	/**
	 * Default constructor.
	 */
	public Discount() {
		this.createdAt = LocalDateTime.now();
	}

	/**
	 * Constructs a discount.
	 * @param title The discount title.
	 * @param value The discount value.
	 * @param isPercentage Whether the discount is a percentage.
	 * @param condition The discount condition.
	 * @param shop The associated shop.
	 */
	public Discount(
		String title,
		Double value,
		boolean isPercentage,
		String condition,
		Shop shop
	) {
		this.shop= shop;
		this.title = title;
		this.value = value;
		this.isPercentage = isPercentage;
		this.condition = condition;
		this.createdAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getKind() {
		return "discount";
	}

	@Override
	public String getPluginId() {
		return "shops";
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getContent() {
		String result = "Reduction : "+title+"\n";
		result+="Obtenez ";
		if (isPercentage){
			result+=value+"%";
		} else {
			result+=value+"€";
		}
		result+=" de reduction pour les conditions suivantes : \n";
		result+=condition;
		return result;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@JsonProperty(value="isPercentage")
	public boolean isPercentage() {
		return isPercentage;
	}

	public void setPercentage(boolean percentage) {
		isPercentage = percentage;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Shop getOwner() {
		return shop;
	}

	public void setOwner(Shop shop) {
		this.shop = shop;
	}

	@Override
	public LocalDateTime getDate() {
		return createdAt;
	}

	@Override
	public boolean equals(Object object) {
		// Same instance.
		if (this == object) {
			return true;
		}

		// Different class.
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		// Same identifier.
		Discount discount = (Discount) object;
		return Objects.equals(id, discount.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return ("Discount{" +
			"id=" + id +
			", title='" + title + '\'' +
			", value=" + value +
			", isPercentage=" + isPercentage +
			", condition='" + condition + '\'' +
			", shop=" + shop +
			", createdAt=" + createdAt +
		'}');
	}
}
