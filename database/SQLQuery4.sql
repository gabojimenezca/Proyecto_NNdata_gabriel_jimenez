USE [NNData]
GO

/****** Object:  Table [dbo].[movements]    Script Date: 19/2/2026 8:37:07 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[movements](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[account_id] [bigint] NULL,
	[balance] [numeric](38, 2) NULL,
	[date] [datetime2](7) NULL,
	[type] [varchar](255) NULL,
	[value] [numeric](38, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[movements]  WITH CHECK ADD  CONSTRAINT [FK1a6nru7corjv5b2vidld4ef5r] FOREIGN KEY([account_id])
REFERENCES [dbo].[accounts] ([id])
GO

ALTER TABLE [dbo].[movements] CHECK CONSTRAINT [FK1a6nru7corjv5b2vidld4ef5r]
GO


