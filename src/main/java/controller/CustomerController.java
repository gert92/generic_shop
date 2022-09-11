package controller;

import dto.Customer;
import repository.CustomerRepository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class CustomerController {

    CustomerRepository repository = new CustomerRepository();

    public Customer addCustomer(){
        String customerName = JOptionPane.showInputDialog("Enter the customers name: ");
        String customerBalance = JOptionPane.showInputDialog("Enter the amount in the customers balance. Example: 544.23");

        Customer customer = Customer.builder()
                .name(customerName)
                .balance(Float.valueOf(customerBalance)).build();

        return repository.createCustomer(customer);

    }

    public List<Customer> findAllCustomers(){
        return repository.showAllCustomer();
    }

    public Customer findCustomerById(Long id){
        return repository.findCustomerById(id);
    }

    public Customer checkIfNewOrExistingCustomer(){
        int answer = JOptionPane.showConfirmDialog(null, "Are you a new Customer?");
        Customer customer = null;
        switch (answer){
            case JOptionPane.YES_OPTION -> customer = addCustomer();
            case JOptionPane.NO_OPTION -> customer = chooseCustomer();
        }
        return customer;
    }

    public Customer chooseCustomer(){
        StringBuilder stringBuilder = new StringBuilder("Select the id of the customer \n");
        List<Customer> customers = findAllCustomers();
        customers.forEach(m -> stringBuilder.append(m.getId()).append(" - ").append(m.getName()).append("\n"));
        int answer = Integer.parseInt(JOptionPane.showInputDialog(stringBuilder));
        Optional<Customer> customer = customers.stream().filter(c -> c.getId() == answer).findFirst();
        return customer.orElse(null);
    }

}
