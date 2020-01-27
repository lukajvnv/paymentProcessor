import { Component, OnInit } from '@angular/core';
import { UserTx } from '../model/user-tx.model';
import { TestService } from '../services/test.service';

@Component({
  selector: 'app-user-tx',
  templateUrl: './user-tx.component.html',
  styleUrls: ['./user-tx.component.scss']
})
export class UserTxComponent implements OnInit {

  private userTxs: UserTx[] = []

  private selectedTx: UserTx;

  constructor(private testService: TestService) { }

  ngOnInit() {
    this.testService.getUserTx().subscribe(data => {
      this.userTxs = data;
    });
  }

  click(tx: UserTx){
    this.selectedTx = tx;
  }

}
