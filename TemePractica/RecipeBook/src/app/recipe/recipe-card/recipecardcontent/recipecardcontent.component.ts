import { Component, Input } from '@angular/core';
import { NzModalRef } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-recipecardcontent',
  templateUrl: './recipecardcontent.component.html',
  styleUrls: ['./recipecardcontent.component.scss']
})
export class RecipecardcontentComponent {
  @Input() recipe: any;
  @Input() modal?: any;
  constructor() { }

  ngOnInit(): void { }
}
