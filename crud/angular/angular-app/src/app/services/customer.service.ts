import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../customer.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private basUrl = "http://localhost:8080/rest/customers"

  constructor(private httpClient: HttpClient) {
  }

  getCustomerList(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(`${this.basUrl}`);
  }

  createCustomer(user: Customer): Observable<Object> {
    return this.httpClient.post(`${this.basUrl}`, user);
  }

  getCustomerById(id: number): Observable<Customer>{
    return this.httpClient.get<Customer>(`${this.basUrl}/${id}`);
  }

  updateCustomer(id:number, user:Customer): Observable<Object>{
    return this.httpClient.put(`${this.basUrl}/${id}`, user);
  }

  deleteCustomer(id:number): Observable<Object>{
    return this.httpClient.delete(`${this.basUrl}/${id}`);
  }
}



