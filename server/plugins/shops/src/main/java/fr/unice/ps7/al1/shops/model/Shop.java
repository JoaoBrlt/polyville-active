package fr.unice.ps7.al1.shops.model;

import fr.unice.ps7.al1.common.model.Establishment;
import org.pf4j.Extension;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Shop.
 * <p>
 * Represents a shop.
 */
@Entity
@Extension
public class Shop implements Establishment {
	/**
	 * The shop identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="shop_sequence")
	@SequenceGenerator(name = "shop_sequence", sequenceName = "shop_sequence", allocationSize=1)
	private Long id;

	/**
	 * The shop name.
	 */
	private String name;

	/**
	 * The shop owner.
	 */
	private String owner;

	/**
	 * The shop type.
	 */
	private String type;

	/**
	 * The shop address.
	 */
	private String address;

	/**
	 * The shop latitude.
	 */
	private Double latitude;

	/**
	 * The shop longitude.
	 */
	private Double longitude;

	/**
	 * The shop discounts.
	 */
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Discount> discounts;

	/**
	 * Default constructor.
	 */
	public Shop() {
		this.discounts = new ArrayList<>();
	}

	/**
	 * Constructs a shop.
	 * @param name The shop name.
	 * @param owner The shop owner.
	 * @param type The shop type.
	 * @param address The shop address.
	 * @param latitude The shop latitude.
	 * @param longitude The shop longitude
	 */
	public Shop(
		String name,
		String owner,
		String type,
		String address,
		double latitude,
		double longitude
	) {
		this();
		this.name = name;
		this.owner = owner;
		this.type = type;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getKind() {
		return "shop";
	}

	@Override
	public String getPluginId() {
		return "shops";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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
		Shop shop = (Shop) object;
		return Objects.equals(id, shop.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return ("Shop{" +
			"id=" + id +
			", name='" + name + '\'' +
			", owner='" + owner + '\'' +
			", type='" + type + '\'' +
			", address='" + address + '\'' +
			", latitude=" + latitude +
			", longitude=" + longitude +
		'}');
	}
}
