package ggc;

import java.io.Serializable;

/**
 * Class representing a Transaction in the system
 */
public abstract class Transaction implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202110252052L;

  /** Transaction's key */
  private int _transactionKey;

  /** Transaction's corresponding base date */
  private int _paymentDate;

  /** Transaction's associated partner */
  private Partner _partner;

  /** Transaction's associated "main" product */
  private Product _product;

  /** Transaction's "main" product amount */
  private int _amount;

  /** Transactions base price - if a Sale, doesn't account for taxes/benefits */
  private double _basePrice;

  /**
   * Main Construtor
   * @param transactionKey
   * @param partner
   * @param product
   * @param baseDate
   * @param amount
   * @param basePrice
   */
  public Transaction(int transactionKey, Partner partner, Product product, int baseDate,
    int amount, double basePrice) {
    _transactionKey = transactionKey;
    _partner = partner;
    _product = product;
    _paymentDate = baseDate;
    _amount = amount;
    _basePrice = basePrice;
  }

  /** @return transaction's key */
  public int getTransactionKey() {
    return _transactionKey;
  }

  /** @return transaction's associated payment/due date */
  public int getPaymentDate() {
    return _paymentDate;
  }

  /**
   * 
   * @return base Price
   */
  public double getBasePrice() {
    return _basePrice;
  }

  /**
   * 
   * @return product
   */
  public Product getProduct() {
    return _product;
  }
  
  /**
   * 
   * @return partner
   */
  public Partner getPartner() {
    return _partner;
  }

  /**
   * 
   * @return amount
   */
  public int getAmount() {
    return _amount;
  }

  /** Used only in Sales and Breakdown Transactions
   *  updates the previously set "due date" to a given one
   * 
   * @param date
   */
  public void updatePaymentDate(int date) {
    _paymentDate = date;
  }

  /**
   * 
   * @return actual price
   */
  public double getActualPrice() {
    return getBasePrice();
  }

  /**
   * Updates actual price
   * @param currentDate
   */
  public void updateActualPrice(int currentDate) { };

  /**
   * Accepts transaction visitor
   * @param visitor
   * @param date
   * @return
   */
  public abstract double accept(TransactionVisitor visitor, int date);

  /**
   * Accepts transaction type checker
   * @param checker
   * @return
   */
  public abstract boolean accept(TransactionChecker checker);

  /**
   * 
   * @return payment date
   */
  public int getDueDate() {
    return _paymentDate;
  }

  /**
   * 
   * @return true
   */
  public boolean isPaid() {
    return true;
  }

  /**
   * 
   * @param price
   */
  public void updatePaid(double price) {
    // do nothing (in general)
  }

  @Override
  public String toString() {
    return String.join(
      "|",
      String.valueOf(_transactionKey),
      _partner.getPartnerKey(),
      _product.getProductKey(),
      String.valueOf(getAmount())
    );
  }

}
