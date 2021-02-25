import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/book';
import { BookService } from 'src/app/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books: Book[];
	 
  constructor(private bookService: BookService) {
    this.bookService.save({name: "The Lord of the Rings"}).subscribe();
  }
	 
  ngOnInit() {
    this.bookService.findAll().subscribe(data => {
      this.books = data;
    });
  }

}
