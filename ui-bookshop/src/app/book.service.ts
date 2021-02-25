import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Book } from './book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private booksUrl: string;
	 
  constructor(private http: HttpClient) {
    this.booksUrl = '/books';
  }
  
  public findAll(): Observable<Book[]> {
    return this.http.get<Book[]>(this.booksUrl);
  }
  
  public save(book: Book) {
    return this.http.post<Book>(this.booksUrl, book);
  }
}
