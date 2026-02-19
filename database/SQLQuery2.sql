USE [NNData]
GO

/****** Object:  Table [dbo].[accounts]    Script Date: 19/2/2026 8:28:26 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[accounts](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[account_number] [varchar](255) NULL,
	[current_balance] [numeric](38, 2) NULL,
	[customer_id] [bigint] NULL,
	[initial_balance] [numeric](38, 2) NULL,
	[status] [bit] NULL,
	[type] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


