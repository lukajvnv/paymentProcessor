import { UserTxItem } from './user-tx-item.model';

export class UserTx {
    userTxId: number;
	
	
	
	created: string;
	
	
	status: string;
	
	items: UserTxItem[];
	
	
	totalAmount: number;
}