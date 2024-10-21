use intelli_edu_application;

INSERT INTO application (id, app_name, description, image_url, type, strategy, audit_status, audit_message, auditor_id,
                         audit_time, user_id, create_time, update_time, deleted)
VALUES (1, '自定义MBTI性格测试', '测试性格', '11', 1, 0, 1, null, null, null, 1, '2024-04-24 15:58:05',
        '2024-05-09 15:09:53', 0);

INSERT INTO application (id, app_name, description, image_url, type, strategy, audit_status, audit_message, auditor_id,
                         audit_time, user_id, create_time, update_time, deleted)
VALUES (2, '自定义得分测试', '测试得分', '22', 0, 0, 1, null, null, null, 1, '2024-04-25 11:39:30',
        '2024-05-09 15:09:53', 0);

INSERT INTO application (id, app_name, description, image_url, type, strategy, audit_status, audit_message, auditor_id,
                         audit_time, user_id, create_time, update_time, deleted)
VALUES (3, 'AI MBTI 性格测试', '快来测测你的 MBTI', '11', 1, 1, 1, null, null, null, 1, '2024-04-26 16:38:12',
        '2024-05-09 15:09:53', 0);

INSERT INTO application (id, app_name, description, image_url, type, strategy, audit_status, audit_message, auditor_id,
                         audit_time, user_id, create_time, update_time, deleted)
VALUES (4, 'AI 得分测试', '看看你熟悉多少首都', '22', 0, 1, 1, null, null, null, 1, '2024-04-26 16:38:56',
        '2024-05-09 15:09:53', 0);


INSERT INTO question (id, questions, app_id, user_id, create_time, update_time, deleted)
VALUES (1,
        '[{"options":[{"evaluation":"I","value":"独自工作","key":"A","grade":0},{"evaluation":"E","value":"与他人合作","key":"B","grade":0}],"title":"1. 你通常更喜欢"},
          {"options":[{"evaluation":"J","value":"喜欢有明确的计划","key":"A","grade":0},{"evaluation":"P","value":"更愿意随机应变","key":"B","grade":0}],"title":"2. 当安排活动时"},
          {"options":[{"evaluation":"T","value":"认为应该严格遵守","key":"A","grade":0},{"evaluation":"F","value":"认为应灵活运用","key":"B","grade":0}],"title":"3. 你如何看待规则"},
          {"options":[{"evaluation":"E","value":"经常是说话的人","key":"A","grade":0},{"evaluation":"I","value":"更倾向于倾听","key":"B","grade":0}],"title":"4. 在社交场合中"},
          {"options":[{"evaluation":"J","value":"先研究再行动","key":"A","grade":0},{"evaluation":"P","value":"边做边学习","key":"B","grade":0}],"title":"5. 面对新的挑战"},
          {"options":[{"evaluation":"S","value":"注重细节和事实","key":"A","grade":0},{"evaluation":"N","value":"注重概念和想象","key":"B","grade":0}],"title":"6. 在日常生活中"},
          {"options":[{"evaluation":"T","value":"更多基于逻辑分析","key":"A","grade":0},{"evaluation":"F","value":"更多基于个人情感","key":"B","grade":0}],"title":"7. 做决定时"},
          {"options":[{"evaluation":"S","value":"喜欢有结构和常规","key":"A","grade":0},{"evaluation":"N","value":"喜欢自由和灵活性","key":"B","grade":0}],"title":"8. 对于日常安排"},
          {"options":[{"evaluation":"P","value":"首先考虑可能性","key":"A","grade":0},{"evaluation":"J","value":"首先考虑后果","key":"B","grade":0}],"title":"9. 当遇到问题时"},
          {"options":[{"evaluation":"T","value":"时间是一种宝贵的资源","key":"A","grade":0},{"evaluation":"F","value":"时间是相对灵活的概念","key":"B","grade":0}],"title":"10. 你如何看待时间"}]',
        1, 1, '2024-04-24 16:41:53', '2024-05-09 12:28:58', 0);


INSERT INTO question (id, questions, app_id, user_id, create_time, update_time, deleted)
VALUES (2,
        '[{"options":[{"grade":0,"value":"利马","key":"A","evaluation":""},{"grade":0,"value":"圣多明各","key":"B","evaluation":""},{"grade":0,"value":"圣萨尔瓦多","key":"C","evaluation":""},{"grade":1,"value":"波哥大","key":"D","evaluation":""}],"title":"哥伦比亚的首都是?"},
          {"options":[{"grade":0,"value":"蒙特利尔","key":"A","evaluation":""},{"grade":0,"value":"多伦多","key":"B","evaluation":""},{"grade":1,"value":"渥太华","key":"C","evaluation":""},{"grade":0,"value":"温哥华","key":"D","evaluation":""}],"title":"加拿大的首都是?"},
          {"options":[{"grade":0,"value":"大阪","key":"A","evaluation":""},{"grade":1,"value":"东京","key":"B","evaluation":""},{"grade":0,"value":"京都","key":"C","evaluation":""},{"grade":0,"value":"名古屋","key":"D","evaluation":""}],"title":"日本的首都是?"},
          {"options":[{"grade":0,"value":"墨尔本","key":"A","evaluation":""},{"grade":0,"value":"悉尼","key":"B","evaluation":""},{"grade":0,"value":"布里斯班","key":"C","evaluation":""},{"grade":1,"value":"堪培拉","key":"D","evaluation":""}],"title":"澳大利亚的首都是?"},
          {"options":[{"grade":1,"value":"雅加达","key":"A","evaluation":""},{"grade":0,"value":"曼谷","key":"B","evaluation":""},{"grade":0,"value":"胡志明市","key":"C","evaluation":""},{"grade":0,"value":"吉隆坡","key":"D","evaluation":""}],"title":"印度尼西亚的首都是?"},
          {"options":[{"grade":0,"value":"上海","key":"A","evaluation":""},{"grade":0,"value":"杭州","key":"B","evaluation":""},{"grade":1,"value":"北京","key":"C","evaluation":""},{"grade":0,"value":"广州","key":"D","evaluation":""}],"title":"中国的首都是?"},
          {"options":[{"grade":0,"value":"汉堡","key":"A","evaluation":""},{"grade":0,"value":"慕尼黑","key":"B","evaluation":""},{"grade":1,"value":"柏林","key":"C","evaluation":""},{"grade":0,"value":"科隆","key":"D","evaluation":""}],"title":"德国的首都是?"},
          {"options":[{"grade":0,"value":"釜山","key":"A","evaluation":""},{"grade":1,"value":"首尔","key":"B","evaluation":""},{"grade":0,"value":"大田","key":"C","evaluation":""},{"grade":0,"value":"仁川","key":"D","evaluation":""}],"title":"韩国的首都是?"},
          {"options":[{"grade":0,"value":"瓜达拉哈拉","key":"A","evaluation":""},{"grade":0,"value":"蒙特雷","key":"B","evaluation":""},{"grade":1,"value":"墨西哥城","key":"C","evaluation":""},{"grade":0,"value":"坎昆","key":"D","evaluation":""}],"title":"墨西哥的首都是?"},
          {"options":[{"grade":1,"value":"开罗","key":"A","evaluation":""},{"grade":0,"value":"亚历山大","key":"B","evaluation":""},{"grade":0,"value":"卢克索","key":"C","evaluation":""},{"grade":0,"value":"卡利乌比亚","key":"D","evaluation":""}],"title":"埃及的首都是?"}]',
        2, 1, '2024-04-25 15:03:07', '2024-05-09 12:28:58', 0);


INSERT INTO question (id, questions, app_id, user_id, create_time, update_time, deleted)
VALUES (3,
        '[{"options":[{"evaluation":"I","value":"独自工作","key":"A","grade":0},{"evaluation":"E","value":"与他人合作","key":"B","grade":0}],"title":"1. 你通常更喜欢"},
          {"options":[{"evaluation":"J","value":"喜欢有明确的计划","key":"A","grade":0},{"evaluation":"P","value":"更愿意随机应变","key":"B","grade":0}],"title":"2. 当安排活动时"},
          {"options":[{"evaluation":"T","value":"认为应该严格遵守","key":"A","grade":0},{"evaluation":"F","value":"认为应灵活运用","key":"B","grade":0}],"title":"3. 你如何看待规则"},
          {"options":[{"evaluation":"E","value":"经常是说话的人","key":"A","grade":0},{"evaluation":"I","value":"更倾向于倾听","key":"B","grade":0}],"title":"4. 在社交场合中"},
          {"options":[{"evaluation":"J","value":"先研究再行动","key":"A","grade":0},{"evaluation":"P","value":"边做边学习","key":"B","grade":0}],"title":"5. 面对新的挑战"},
          {"options":[{"evaluation":"S","value":"注重细节和事实","key":"A","grade":0},{"evaluation":"N","value":"注重概念和想象","key":"B","grade":0}],"title":"6. 在日常生活中"},
          {"options":[{"evaluation":"T","value":"更多基于逻辑分析","key":"A","grade":0},{"evaluation":"F","value":"更多基于个人情感","key":"B","grade":0}],"title":"7. 做决定时"},
          {"options":[{"evaluation":"S","value":"喜欢有结构和常规","key":"A","grade":0},{"evaluation":"N","value":"喜欢自由和灵活性","key":"B","grade":0}],"title":"8. 对于日常安排"},
          {"options":[{"evaluation":"P","value":"首先考虑可能性","key":"A","grade":0},{"evaluation":"J","value":"首先考虑后果","key":"B","grade":0}],"title":"9. 当遇到问题时"},
          {"options":[{"evaluation":"T","value":"时间是一种宝贵的资源","key":"A","grade":0},{"evaluation":"F","value":"时间是相对灵活的概念","key":"B","grade":0}],"title":"10. 你如何看待时间"}]',
        1, 1, '2024-04-24 16:41:53', '2024-05-09 12:28:58', 0);


INSERT INTO question (id, questions, app_id, user_id, create_time, update_time, deleted)
VALUES (4,
        '[{"options":[{"grade":0,"value":"利马","key":"A","evaluation":""},{"grade":0,"value":"圣多明各","key":"B","evaluation":""},{"grade":0,"value":"圣萨尔瓦多","key":"C","evaluation":""},{"grade":1,"value":"波哥大","key":"D","evaluation":""}],"title":"哥伦比亚的首都是?"},
          {"options":[{"grade":0,"value":"蒙特利尔","key":"A","evaluation":""},{"grade":0,"value":"多伦多","key":"B","evaluation":""},{"grade":1,"value":"渥太华","key":"C","evaluation":""},{"grade":0,"value":"温哥华","key":"D","evaluation":""}],"title":"加拿大的首都是?"},
          {"options":[{"grade":0,"value":"大阪","key":"A","evaluation":""},{"grade":1,"value":"东京","key":"B","evaluation":""},{"grade":0,"value":"京都","key":"C","evaluation":""},{"grade":0,"value":"名古屋","key":"D","evaluation":""}],"title":"日本的首都是?"},
          {"options":[{"grade":0,"value":"墨尔本","key":"A","evaluation":""},{"grade":0,"value":"悉尼","key":"B","evaluation":""},{"grade":0,"value":"布里斯班","key":"C","evaluation":""},{"grade":1,"value":"堪培拉","key":"D","evaluation":""}],"title":"澳大利亚的首都是?"},
          {"options":[{"grade":1,"value":"雅加达","key":"A","evaluation":""},{"grade":0,"value":"曼谷","key":"B","evaluation":""},{"grade":0,"value":"胡志明市","key":"C","evaluation":""},{"grade":0,"value":"吉隆坡","key":"D","evaluation":""}],"title":"印度尼西亚的首都是?"},
          {"options":[{"grade":0,"value":"上海","key":"A","evaluation":""},{"grade":0,"value":"杭州","key":"B","evaluation":""},{"grade":1,"value":"北京","key":"C","evaluation":""},{"grade":0,"value":"广州","key":"D","evaluation":""}],"title":"中国的首都是?"},
          {"options":[{"grade":0,"value":"汉堡","key":"A","evaluation":""},{"grade":0,"value":"慕尼黑","key":"B","evaluation":""},{"grade":1,"value":"柏林","key":"C","evaluation":""},{"grade":0,"value":"科隆","key":"D","evaluation":""}],"title":"德国的首都是?"},
          {"options":[{"grade":0,"value":"釜山","key":"A","evaluation":""},{"grade":1,"value":"首尔","key":"B","evaluation":""},{"grade":0,"value":"大田","key":"C","evaluation":""},{"grade":0,"value":"仁川","key":"D","evaluation":""}],"title":"韩国的首都是?"},
          {"options":[{"grade":0,"value":"瓜达拉哈拉","key":"A","evaluation":""},{"grade":0,"value":"蒙特雷","key":"B","evaluation":""},{"grade":1,"value":"墨西哥城","key":"C","evaluation":""},{"grade":0,"value":"坎昆","key":"D","evaluation":""}],"title":"墨西哥的首都是?"},
          {"options":[{"grade":1,"value":"开罗","key":"A","evaluation":""},{"grade":0,"value":"亚历山大","key":"B","evaluation":""},{"grade":0,"value":"卢克索","key":"C","evaluation":""},{"grade":0,"value":"卡利乌比亚","key":"D","evaluation":""}],"title":"埃及的首都是?"}]',
        2, 1, '2024-04-25 15:03:07', '2024-05-09 12:28:58', 0);


