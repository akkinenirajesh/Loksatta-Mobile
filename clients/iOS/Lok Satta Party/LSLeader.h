//
//  LSLeader.h
//  Lok Satta Party
//
//  Created by Vimukti 22 on 2/2/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LSLeader : NSObject
{
    NSString *name;
    NSString *imageName;
    NSString *about;
}

@property(nonatomic,retain) NSString *name;
@property(nonatomic,retain) NSString *imageName;
@property(nonatomic,retain) NSString *about;

@end
