//
//  LSNewsFeed.h
//  Lok Satta Party
//
//  Created by Vimukti 22 on 2/2/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LSLeader.h"

@interface LSNewsFeed : NSObject
{
    LSLeader *leader;
    NSString *content;
    NSString *dateCreated;
}
@property (nonatomic, retain) LSLeader	 *leader;
@property (nonatomic, retain) NSString	 *content;
@property (nonatomic, retain) NSString	 *dateCreated;

@end
