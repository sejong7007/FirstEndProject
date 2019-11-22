package com.testtwo.web.pxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Lazy
@Component("pager")
public class PageProxy extends Proxy {

	@Autowired CrawlingProxy crawler;
	
	private int rowCount, startRow, endRow,
				pageCount, pageSize, nowPage, startPage, endPage,
				blockCount, blockSize, nowBlock, prevBlock, nextBlock;
	private boolean existPrev, existNext;
	
	public void paging() {
		//rowCount, nowPage, blockSize, pageSize
		pageCount = rowCount%pageSize!=0 ? rowCount/pageSize+1 : rowCount/pageSize ;
		blockCount = pageCount%blockSize!=0 ? pageCount/blockSize+1 : pageCount/blockSize;
		nowBlock = nowPage/blockSize;
		startRow = nowPage*pageSize;
		endRow = nowPage!=(pageCount-1)? startRow+pageSize-1 : rowCount-1;
		startPage = nowBlock*blockSize;
		endPage = nowBlock != (blockCount-1)? startPage+blockSize-1 : pageCount-1;
		prevBlock = startPage - blockSize;
		nextBlock = startPage + blockSize;
		
		existPrev = nowBlock != 0;
		existNext = nowBlock != (blockCount-1);
		
	}
	
}
